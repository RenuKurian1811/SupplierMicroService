package com.nineleaps.ecommerce.supplier.consumer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.nineleaps.ecommerce.supplier.beans.ConsumerOrderModel;
import com.nineleaps.ecommerce.supplier.beans.ItemsModel;
import com.nineleaps.ecommerce.supplier.beans.OrderModel;
import com.nineleaps.ecommerce.supplier.beans.ProductModel;
import com.nineleaps.ecommerce.supplier.beans.SupplierModel;
import com.nineleaps.ecommerce.supplier.controller.FeignController;
import com.nineleaps.ecommerce.supplier.entity.SupplierEntity;
import com.nineleaps.ecommerce.supplier.service.EmailService;
import com.nineleaps.ecommerce.supplier.service.SupplierService;

@Service
public class KafkaConsumerService {

	@Autowired(required = true)
	private FeignController feignController;

	@Autowired
	EmailService emailService;

	@Autowired
	SupplierService supplierService;

	@KafkaListener(topics = "orderTopic", groupId = "sample-group", containerFactory = "kafkaListener")
	public void consume(ConsumerOrderModel order) {
		try {
			System.out.println("Consumed Message :" + order);

			List<ItemsModel> itemsList = new ArrayList<>();
			List<String> supplierIds = new ArrayList<>();
			String productId = null;
			Map<String, OrderModel> mapForMail = new HashMap<>();

			int itemSize = order.getItem().size();
			for (int i = 0; i < itemSize; i++) {
				productId = order.getItem().get(i).getProductId();
				OrderModel orderForMail = new OrderModel();

				ProductModel product = feignController.checkProductAvailability(productId);
				if (null != product) {
					orderForMail.setTotal(product.getPrice());
					orderForMail.setProductName(product.getName());
					orderForMail.setProductId(product.getProductId());
					orderForMail.setDescription(product.getDescription());
					orderForMail.setSupplierId(product.getSupplierId());
					orderForMail.setCustomerAddress(order.getCustomerAddress());
					orderForMail.setCustomerName(order.getCustomerName());
					orderForMail.setQuantity(order.getItem().get(i).getQuantity());
					orderForMail.setOrderId(order.getId());
					orderForMail.setCustomerEmail(order.getCustomerEmail());
					orderForMail.setCustomerAddress(order.getCustomerAddress());

					mapForMail.put(product.getSupplierId(), orderForMail);
				}
			}
			getSupplierDetails(mapForMail);
		//	mapForMail = getSupplierDetails(mapForMail);
		//	emailService.sendEmail(mapForMail);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void getSupplierDetails(Map<String, OrderModel> mapForMail) {
		try {
			for (Map.Entry<String, OrderModel> details : mapForMail.entrySet()) {
				String supplierId = details.getKey();
				SupplierEntity supplierDetails = (SupplierEntity) supplierService.getSupplierDetails(supplierId);
				String supplierEmail = supplierDetails.getEmail();
				emailService.sendEmail(supplierId, details.getValue(), supplierEmail);
//				return mapForMail;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//return mapForMail;
	}

}