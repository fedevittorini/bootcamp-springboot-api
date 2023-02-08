package com.eduit.bootcamp.springbootapi.db.repository;

import org.springframework.data.repository.Repository;

import com.eduit.bootcamp.springbootapi.db.entity.UserEntity;

public interface UserRepository extends Repository<UserEntity, Long> {

	UserEntity findOneByUsername(String username);
}
