package com.eduit.bootcamp.springbootapi.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.eduit.bootcamp.springbootapi.db.entity.UserEntity;
import com.eduit.bootcamp.springbootapi.model.UserDTO;
import com.eduit.bootcamp.springbootapi.utils.DateUtils;

public class UserMapperImpl implements UserMapper {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserAdministrationServiceImpl.class);
	
	private PasswordEncoder encoder;
	
	public UserMapperImpl(PasswordEncoder theEncoder) {
		encoder = theEncoder;
	}

	public UserEntity mapUser(final UserDTO theUser) {
		LOGGER.trace(String.format("Mapping UserDTO with attributes: %s to: UserEntity", theUser.toString()));
		UserEntity response = new UserEntity();
		response.setId(theUser.getId());
		response.setUsername(theUser.getUsername());
		response.setPassword(theUser.getPassword());
		response.setFirstName(theUser.getFirstName());
		response.setLastName(theUser.getLastName());
		response.setEmail(theUser.getEmail());
		if (theUser.getDateCreated() != null) {
			response.setDateCreated(DateUtils.toDate(theUser.getDateCreated()));
		}
		if (theUser.getDateDeleted() != null) {
			response.setDateDeleted(DateUtils.toDate(theUser.getDateDeleted()));
		}
		
		return response;
	}
	
	public UserEntity mapUserEncoded(final UserDTO theUser) {
		LOGGER.trace(String.format("Mapping encoded UserDTO with attributes: %s to: UserEntity", theUser.toString()));
		UserEntity response = new UserEntity();
		response.setId(theUser.getId());
		response.setUsername(theUser.getUsername());
		response.setPassword(encoder.encode(theUser.getPassword()));
		response.setFirstName(theUser.getFirstName());
		response.setLastName(theUser.getLastName());
		response.setEmail(theUser.getEmail());
		if (theUser.getDateCreated() != null) {
			response.setDateCreated(DateUtils.toDate(theUser.getDateCreated()));
		}
		if (theUser.getDateDeleted() != null) {
			response.setDateDeleted(DateUtils.toDate(theUser.getDateDeleted()));
		}
		
		return response;
	}
	
	
	public UserDTO mapUser(final UserEntity theUser) {
		LOGGER.trace(String.format("Mapping UserDTO with attributes: %s to: UserEntity", theUser.toString()));
		UserDTO response = new UserDTO();
		response.setId(theUser.getId());
		response.setUsername(theUser.getUsername());
		response.setPassword(theUser.getPassword());
		response.setFirstName(theUser.getFirstName());
		response.setLastName(theUser.getLastName());
		response.setEmail(theUser.getEmail());
		if (theUser.getDateCreated() != null) {
			LocalDate createdLocalDate = DateUtils.toLocalDate(theUser.getDateCreated());
			response.setDateCreated(createdLocalDate);
		}
		if (theUser.getDateDeleted() != null) {
			LocalDate deletedLocalDate = DateUtils.toLocalDate(theUser.getDateDeleted());
			response.setDateDeleted(deletedLocalDate);
		}
		
		return response;
	}

}