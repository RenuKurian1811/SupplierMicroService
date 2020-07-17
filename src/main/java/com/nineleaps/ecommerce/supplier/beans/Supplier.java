package com.nineleaps.ecommerce.supplier.beans;

import org.springframework.stereotype.Component;

@Component
public class Supplier {
	private String supplierId;
	private String name;
	private String email;
	
	public String getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Supplier(String supplierId, String name, String email) {
		super();
		this.supplierId = supplierId;
		this.name = name;
		this.email = email;
	}
	
}
