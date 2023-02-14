package com.eduit.bootcamp.springbootapi.service;

import com.eduit.bootcamp.springbootapi.db.entity.UserEntity;

public interface UserAuthenticationService {

	String getToken(final Long id, final String username);
	
	UserEntity login(final String username, final String password);
	
}
