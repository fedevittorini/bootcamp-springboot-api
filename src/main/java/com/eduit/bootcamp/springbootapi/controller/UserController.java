package com.eduit.bootcamp.springbootapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.eduit.bootcamp.springbootapi.api.UsersApiDelegate;
import com.eduit.bootcamp.springbootapi.model.ErrorItemDTO;
import com.eduit.bootcamp.springbootapi.model.ResponseContainerUserResponseDTO;
import com.eduit.bootcamp.springbootapi.model.UserDTO;
import com.eduit.bootcamp.springbootapi.service.UserAdministrationService;
import com.eduit.bootcamp.springbootapi.service.UserAuthenticationService;

public class UserController implements UsersApiDelegate {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	private UserAdministrationService userAdministrationService;
	private UserAuthenticationService userAuthenticationService;
	
	public UserController(UserAdministrationService theUserAdministrationService, 
				UserAuthenticationService theUserAuthenticationService) {
		userAuthenticationService = theUserAuthenticationService;
		userAdministrationService = theUserAdministrationService;
	}

	public ResponseEntity<ResponseContainerUserResponseDTO> createUser(UserDTO userDTO) {
		LOGGER.debug("CREAR");
		ResponseContainerUserResponseDTO responseContainer = new ResponseContainerUserResponseDTO();
		try {
			UserDTO response = userAdministrationService.createUser(userDTO);
			responseContainer.data(response);
			return new ResponseEntity<ResponseContainerUserResponseDTO>(responseContainer, HttpStatus.CREATED);
		} catch (Exception e) {
			LOGGER.error("An error occurred creating a user", e);
			List<ErrorItemDTO> errorList = new ArrayList<>();
			ErrorItemDTO errorItem = new ErrorItemDTO();
			errorItem.setCode("A2");
			errorItem.setDetail(e.getMessage());
			errorList.add(errorItem);
			responseContainer.errors(errorList);
			return new ResponseEntity<ResponseContainerUserResponseDTO>(responseContainer, HttpStatus.BAD_REQUEST);
		}
	}
	
	public ResponseEntity<List<UserDTO>> retrieveAllUsers() {
		LOGGER.debug("LISTAR");
		try {
			List<UserDTO> response = userAdministrationService.listUsers();
			return new ResponseEntity<List<UserDTO>>(response, HttpStatus.CREATED);
		} catch (Exception e) {
			LOGGER.error("An error occurred listing users", e);
			return new ResponseEntity<List<UserDTO>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	public ResponseEntity<ResponseContainerUserResponseDTO> login(String username,
	        String password) {
		LOGGER.debug("LOGIN");
//		UserEntity usr = userAuthenticationService.login(username, password);
//		
//		String token = userAuthenticationService.getToken(usr.getId(), username);
//		UserDTO u = new UserDTO();
//		u.setUsername(username);
//		MultiValueMap<String, String> headers = new HttpHeaders();
//		headers.add("X-Auth-Token", token);
//		ResponseEntity<UserDTO> response = new ResponseEntity<UserDTO>(u, headers, HttpStatus.CREATED);
		return new ResponseEntity<ResponseContainerUserResponseDTO>(HttpStatus.ACCEPTED);
	}
	
}
