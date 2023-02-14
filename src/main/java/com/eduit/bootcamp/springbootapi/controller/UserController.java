package com.eduit.bootcamp.springbootapi.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import com.eduit.bootcamp.springbootapi.api.UsersApiDelegate;
import com.eduit.bootcamp.springbootapi.db.entity.UserEntity;
import com.eduit.bootcamp.springbootapi.model.UserDTO;
import com.eduit.bootcamp.springbootapi.service.UserAuthenticationService;

public class UserController implements UsersApiDelegate {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	private UserAuthenticationService userAuthenticationService;
	
	public UserController(UserAuthenticationService theUserAuthenticationService) {
		userAuthenticationService = theUserAuthenticationService;
	}

	public ResponseEntity<UserDTO> createUser(String authorization,
	        UserDTO userDTO) {
		logger.debug("CREAR");
		return new ResponseEntity<UserDTO>(HttpStatus.CREATED);
	}
	
	public ResponseEntity<List<UserDTO>> retrieveAllUsers(String authorization) {
		logger.debug("LISTAR");
		return new ResponseEntity<List<UserDTO>>(HttpStatus.CREATED);
	}
	
	public ResponseEntity<UserDTO> login(String username,
	        String password) {
		logger.debug("LOGIN");
		UserEntity usr = userAuthenticationService.login(username, password);
		
		String token = userAuthenticationService.getToken(usr.getId(), username);
		UserDTO u = new UserDTO();
		u.setUsername(username);
		MultiValueMap<String, String> headers = new HttpHeaders();
		headers.add("X-Auth-Token", token);
		ResponseEntity<UserDTO> response = new ResponseEntity<UserDTO>(u, headers, HttpStatus.CREATED);
		return response;
	}
	
}
