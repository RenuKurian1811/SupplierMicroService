package com.nineleaps.ecommerce.supplier.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.nineleaps.ecommerce.supplier.beans.ProductModel;


@FeignClient(name="ProductMicroService", url = "http://localhost:8081")
public interface FeignController {

	@GetMapping(value= "/product/getProduct/{id}")
	public ProductModel checkProductAvailability(@PathVariable String id);
}
