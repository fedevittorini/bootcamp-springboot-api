package com.eduit.bootcamp.springbootapi.db.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.eduit.bootcamp.springbootapi.db.entity.ProductEntity;

public interface ProductRepository extends Repository<ProductEntity, Long> {

	List<ProductEntity> findByCategory(ProductEntity parentCategory);
	
}
