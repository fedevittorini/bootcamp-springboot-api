package com.eduit.bootcamp.springbootapi.db.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.eduit.bootcamp.springbootapi.db.entity.CategoryEntity;

public interface CategoryRepository extends Repository<CategoryEntity, Long> {
	
	List<CategoryEntity> findByParent(CategoryEntity parentCategory);
	
	CategoryEntity findOneByName(String username);
}
