package com.nineleaps.ecommerce.supplier.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.nineleaps.ecommerce.supplier.beans.OrderModel;

@Service
public class EmailService {
	
	private JavaMailSender javaMailSender;
	
	@Autowired
	public EmailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	
	public void sendEmail(String supplierId, OrderModel order, String supplierEmail) throws MailException {
		
		String mailContent = null; 
		mailContent = "The following order has been placed today.\n" +"Order Id - " + order.getOrderId() +
				" ,ProductId - " + order.getProductId() + " ,Product Name - " + order.getProductName() +
     		 		" ,Quantity - " + order.getQuantity() ;
		
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(supplierEmail);
		mail.setSubject("Testing Mail API");
		mail.setText(mailContent);
		javaMailSender.send(mail);
		System.out.println("Mail has been send to the supplier.");
	}
	
}
