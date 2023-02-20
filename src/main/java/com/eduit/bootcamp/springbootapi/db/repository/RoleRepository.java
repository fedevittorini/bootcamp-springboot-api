package com.eduit.bootcamp.springbootapi.db.repository;

import org.springframework.data.repository.CrudRepository;

import com.eduit.bootcamp.springbootapi.db.entity.RoleEntity;

public interface RoleRepository extends CrudRepository<RoleEntity, Long> {

}
