package com.nineleaps.ecommerce.supplier.entity;


import java.io.Serializable;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.stereotype.Component;

@Component
@Table("SUPPLIER")

public class SupplierEntity implements Serializable {

	@PrimaryKey private String supplierId;
	@Column("name")
	private String name;
	@Column("email")
	private String email;
	
	
	public SupplierEntity() {
		
	}


	public SupplierEntity(String supplierId, String name, String email) {
		super();
		this.supplierId = supplierId;
		this.name = name;
		this.email = email;
	}


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
	
	


			
}