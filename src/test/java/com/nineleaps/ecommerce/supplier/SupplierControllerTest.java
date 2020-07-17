package com.nineleaps.ecommerce.supplier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nineleaps.ecommerce.supplier.controller.SupplierController;
import com.nineleaps.ecommerce.supplier.entity.SupplierEntity;
import com.nineleaps.ecommerce.supplier.exception.SupplierException;
import com.nineleaps.ecommerce.supplier.service.SupplierService;


@ExtendWith(SpringExtension.class)
@WebMvcTest(SupplierController.class)
public class SupplierControllerTest {
	@Autowired
	SupplierController supplierController;

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private SupplierService supplierService;

	@MockBean
	SupplierEntity supplier;
	
	@MockBean
	SupplierException supplierException;
	
	@BeforeEach
	void setupThis() {
		System.out.println("@BeforeEach executed");
	}
	
//	Product productStub = new Product("P1","Pname1","100","Desc1", "S1");
	SupplierEntity supplierEntityStub = new SupplierEntity("S1","Sname1","S1email@xyz.com");

	
	@Test
	public void testCreateSupplierSuccess() throws Exception {

		//Product productStub = new Product("P1","Pname1","100","Desc1", "S1");
		mockMvc.perform(post("/supplier/create")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(asJsonString((supplierEntityStub))))
				.andExpect(status().isCreated());
		
	}	
	
	@Test
	public void testGetSupplierSuccess() throws Exception {

		//Product productStub = new Product("P1","Pname1","100","Desc1", "S1");
		when(supplierService.getSupplierDetails(anyString())).thenReturn(supplierEntityStub);
		
		mockMvc.perform(get("/supplier/getSupplier/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		
	}	
	@Test
	public void testGetSupplierFailure() throws Exception {

		//Product productStub = new Product("P1","Pname1","100","Desc1", "S1");
		SupplierEntity supplierEntityStub = null;
		when(supplierService.getSupplierDetails(anyString())).thenReturn(supplierEntityStub);
		
		mockMvc.perform(get("/supplier/getSupplier/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
		
	}	
	
	@Test
	public void testUpdateSupplierSuccess() throws Exception {

		//Product productStub = null;
		when(supplierService.updateSupplierDetails(anyString(),any(SupplierEntity.class))).thenReturn(supplierEntityStub);
		mockMvc.perform(put("/supplier/updateSupplier/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString((supplierEntityStub))))
				.andExpect(status().isOk());
	}	
	
	@Test
	public void testUpdateSupplierFailure() throws Exception {

		when(supplierService.updateSupplierDetails(anyString(),any(SupplierEntity.class))).thenReturn("");
		mockMvc.perform(put("/supplier/updateSupplier/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString((supplierEntityStub))))
				.andExpect(status().isNotFound());
	}
	
	@Test
	public void testDeleteSupplierSuccess() throws Exception {

		//Product productStub = null;
		mockMvc.perform(delete("/supplier/deleteSupplier/1"))
				.andExpect(status().isOk());
	}	
	
	@Test
	public void testGetAllSupplierSuccess() throws Exception {
		SupplierEntity productStub = new SupplierEntity("S1","Sname1","S1email@xyz.com");
		List<SupplierEntity> suppliers = new ArrayList<SupplierEntity>();
		suppliers.add(productStub);
		when(supplierService.getallSupplierDetails()).thenReturn(suppliers);
		mockMvc.perform(get("/supplier/viewSuppliers"))
				.andExpect(status().isOk());
	}
	
	@Test
	public void testGetAllSupplierFailure() throws Exception {
		SupplierEntity productStub = new SupplierEntity("S1","Sname1","S1email@xyz.com");
		List<SupplierEntity> suppliers = new ArrayList<SupplierEntity>();
		when(supplierService.getallSupplierDetails()).thenReturn(suppliers);
		mockMvc.perform(get("/supplier/viewSuppliers"))
				.andExpect(status().isNotFound());
	}
	
	 static String asJsonString(final Object obj) {
	        try {
	            return new ObjectMapper().writeValueAsString(obj);
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    }
}
