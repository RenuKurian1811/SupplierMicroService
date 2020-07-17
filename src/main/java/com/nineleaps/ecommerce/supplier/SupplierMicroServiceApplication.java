package com.nineleaps.ecommerce.supplier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(exclude = KafkaAutoConfiguration.class)
@EnableDiscoveryClient
@EnableFeignClients("com.nineleaps.ecommerce.supplier")
public class SupplierMicroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SupplierMicroServiceApplication.class, args);
	}

}
