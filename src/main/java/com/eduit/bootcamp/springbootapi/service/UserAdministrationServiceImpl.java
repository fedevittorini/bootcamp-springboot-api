package com.eduit.bootcamp.springbootapi.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eduit.bootcamp.springbootapi.db.entity.UserEntity;
import com.eduit.bootcamp.springbootapi.db.repository.UserRepository;
import com.eduit.bootcamp.springbootapi.model.UserDTO;

public class UserAdministrationServiceImpl implements UserAdministrationService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserAdministrationServiceImpl.class);
	
	private UserRepository userRepository;
	
	private UserMapper userMapper;
	
	public UserAdministrationServiceImpl(UserMapper theUserMapper, UserRepository theUserRepository) {
		userRepository = theUserRepository;
		userMapper = theUserMapper;
	}

	@Override
	public UserDTO createUser(UserDTO theUser) {
		Validate.notNull(theUser, "The user cannot be null.");
		LOGGER.trace(String.format("Creating user with attributes: %s", theUser.toString()));
		UserEntity toCreate = userMapper.mapUserEncoded(theUser);
		UserEntity created = userRepository.save(toCreate);
		return userMapper.mapUser(created);
	}

	@Override
	public List<UserDTO> listUsers() {
		LOGGER.trace(String.format("Listing all users"));
		Iterable<UserEntity> users = userRepository.findAll();
		Iterator<UserEntity> iter = users.iterator();
		List<UserDTO> response = new ArrayList<>();
		while (iter.hasNext()) {
			response.add(userMapper.mapUser(iter.next()));
		}
		return response;
	}
	
}
