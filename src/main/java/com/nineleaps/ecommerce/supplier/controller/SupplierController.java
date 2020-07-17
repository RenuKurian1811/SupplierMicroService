package com.nineleaps.ecommerce.supplier.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nineleaps.ecommerce.supplier.entity.SupplierEntity;
import com.nineleaps.ecommerce.supplier.exception.SupplierException;
import com.nineleaps.ecommerce.supplier.service.SupplierService;

@RestController
@RequestMapping("/supplier")
public class SupplierController {

	@Autowired
	private SupplierService supplierService;
	
	@Autowired
	SupplierEntity supplier;
	
	@Autowired
	SupplierException supplierException;
	
	@PostMapping("/create")
	public	ResponseEntity<?> createSupplier(@RequestBody SupplierEntity supplier){
		return new ResponseEntity<>(supplierService.createSupplier(supplier), HttpStatus.CREATED);
	}
	
	@GetMapping(path = "/getSupplier/{id}")
	ResponseEntity<?> getSupplier(@PathVariable("id") String sId){
		ResponseEntity<?> responseEntity = null;
		try {
			Object resp = supplierService.getSupplierDetails(sId);
			if(resp instanceof SupplierEntity) {
				responseEntity =  new ResponseEntity<>(resp,HttpStatus.OK);
			}
			else {
				responseEntity =  new ResponseEntity<>(resp,HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseEntity;
	}
	
	@PutMapping(path = "/updateSupplier/{id}")
	ResponseEntity<?> updateSupplier(@PathVariable("id") String sId, @RequestBody SupplierEntity newSupplier){
		ResponseEntity<?> responseEntity = null;
		try {
			Object response = supplierService.updateSupplierDetails(sId,newSupplier);
			if(response instanceof  SupplierEntity) {
				responseEntity =  new ResponseEntity<>(response,HttpStatus.OK);
			}
			else {
				responseEntity =  new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			e.printStackTrace();
		
		}
		return responseEntity;
	}
	
	@DeleteMapping("/deleteSupplier/{id}")
	public ResponseEntity<?> deleteSupplier(@PathVariable("id") String sId) {
		try {
			supplierService.deleteSupplierDetails(sId);
			supplierException.setCode("200");
			supplierException.setMessage("Deleted");
			return new ResponseEntity<>(supplierException , HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@GetMapping("/viewSuppliers")
	public ResponseEntity<List<SupplierEntity>> getAllSupplier() {
		ResponseEntity<?> responseEntity = null;
		try {
			List<SupplierEntity> suppliers;
			suppliers = supplierService.getallSupplierDetails();
			if (suppliers.isEmpty()) {
				supplierException.setCode("404");
				supplierException.setMessage("No details found");
				responseEntity =  new ResponseEntity<>(supplierException, HttpStatus.NOT_FOUND);
			}
			else {
				responseEntity = new ResponseEntity<>(suppliers, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return (ResponseEntity<List<SupplierEntity>>) responseEntity;
	}
}
