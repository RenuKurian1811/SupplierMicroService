package com.nineleaps.ecommerce.supplier.exception;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class SupplierException implements Serializable{

	public String message;
	public String code;
	
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public SupplierException() {

	}

	public SupplierException(String message, String code) {
		super();
		this.message = message;
		this.code = code;
	}
	
	

}
