package com.nineleaps.ecommerce.supplier.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nineleaps.ecommerce.supplier.entity.SupplierEntity;
import com.nineleaps.ecommerce.supplier.exception.SupplierException;
import com.nineleaps.ecommerce.supplier.repository.SupplierRepository;

@Service
public class SupplierService {

	@Autowired
	private SupplierRepository supplierRepository;

	public Object createSupplier(SupplierEntity supplier) {
		SupplierEntity entity = supplierRepository.save(supplier);
		return entity;
	}

	public Object getSupplierDetails(String sId) {
		Optional<SupplierEntity> supplierEntity = supplierRepository.findById(sId);
		SupplierException supplierException = new SupplierException();
		if (!supplierEntity.isPresent()) {
			supplierException.setMessage("No Record found");
			supplierException.setCode("204");
			return supplierException;
		}
		else {
			return supplierEntity.get();
		}
	}

	public Object updateSupplierDetails(String sId, SupplierEntity newSupplier) throws Exception {
		SupplierException exception = new SupplierException();
		SupplierEntity updatedProduct = null;
		Optional<SupplierEntity> productPresent = supplierRepository.findById(sId);
		if (!productPresent.isPresent()) {
			//throw new Exception("No Existing Record found!");
			exception.setCode("204");
			exception.setMessage("No Existing Record found!");
			return exception;
		}
		else {
			supplierRepository.deleteById(sId);
			updatedProduct = updateRecordIntoDB(newSupplier);
			return updatedProduct;
		}
	}

	private SupplierEntity updateRecordIntoDB(SupplierEntity newSupplier) {
		SupplierEntity supplierEntity = supplierRepository.save(newSupplier);
		return supplierEntity;
	}

	public void deleteSupplierDetails(String sId) {
		supplierRepository.deleteById(sId);
		}

	public List<SupplierEntity> getallSupplierDetails() {
		List<SupplierEntity> suppliers = new ArrayList<SupplierEntity>();
		supplierRepository.findAll().forEach(suppliers::add);
		return suppliers;
	}
}
