package com.nineleaps.ecommerce.supplier.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;

import com.nineleaps.ecommerce.supplier.entity.SupplierEntity;

public interface SupplierRepository extends CassandraRepository<SupplierEntity,String> {
	// Optional<SupplierEntity> findById(String id);
	
}
