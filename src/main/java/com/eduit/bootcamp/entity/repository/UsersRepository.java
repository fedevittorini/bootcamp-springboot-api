package com.eduit.bootcamp.entity.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.eduit.bootcamp.entity.Users;

public interface UsersRepository extends Repository<Users, Long> {
	
	List<Users> findByFirstName(String firstName);
	
	List<Users> findByFirstNameAndLastName(String firstName, String lastName);

}
