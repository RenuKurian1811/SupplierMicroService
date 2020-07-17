package com.nineleaps.ecommerce.supplier.beans;



	import java.util.List;

	import org.springframework.data.cassandra.core.mapping.CassandraType;
	import org.springframework.data.cassandra.core.mapping.Column;

	import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
	import com.fasterxml.jackson.annotation.JsonProperty;


	@JsonIgnoreProperties(ignoreUnknown = true)
	public class ConsumerOrderModel {
		public ConsumerOrderModel() {
			// TODO Auto-generated constructor stub
		}
		public ConsumerOrderModel(String id, String date, String customerName, String customerEmail, String customerAddress,
				List<ItemsModel> item, double total) {
			super();
			this.id = id;
			this.date = date;
			this.customerName = customerName;
			this.customerEmail = customerEmail;
			this.customerAddress = customerAddress;
			this.item = item;
			this.total = total;
		}
		private String id;
		private String date;
		private String customerName;
		private String customerEmail;
		private String customerAddress;
		@JsonProperty(value="item")
		private List<ItemsModel> item;
		private double total;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getDate() {
			return date;
		}
		public void setDate(String date) {
			this.date = date;
		}
		public String getCustomerName() {
			return customerName;
		}
		public void setCustomerName(String customerName) {
			this.customerName = customerName;
		}
		public String getCustomerEmail() {
			return customerEmail;
		}
		public void setCustomerEmail(String customerEmail) {
			this.customerEmail = customerEmail;
		}
		public String getCustomerAddress() {
			return customerAddress;
		}
		public void setCustomerAddress(String customerAddress) {
			this.customerAddress = customerAddress;
		}
		public List<ItemsModel> getItem() {
			return item;
		}
		public void setItem(List<ItemsModel> item) {
			this.item = item;
		}
		public double getTotal() {
			return total;
		}
		public void setTotal(double total) {
			this.total = total;
		}
		
		
	}
