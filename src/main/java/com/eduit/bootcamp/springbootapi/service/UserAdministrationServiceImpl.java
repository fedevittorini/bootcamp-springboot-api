package com.eduit.bootcamp.springbootapi.service;

import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import com.eduit.bootcamp.springbootapi.db.entity.UserEntity;
import com.eduit.bootcamp.springbootapi.db.repository.UserRepository;
import com.eduit.bootcamp.springbootapi.model.UserDTO;

public class UserAdministrationServiceImpl implements UserAdministrationService {

	private UserRepository userRepository;
	
	public UserAdministrationServiceImpl(UserRepository theUserRepository) {
		userRepository = theUserRepository;
	}

	@Override
	public UserDTO createUser(UserDTO theUser) {
		UserEntity toCreate = mapUser(theUser);
		UserEntity created = userRepository.save(toCreate);
		return mapUser(created);
	}

	@Override
	public List<UserDTO> listUsers() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	private UserEntity mapUser(final UserDTO theUser) {
		UserEntity response = new UserEntity();
		response.setId(theUser.getId());
		response.setUsername(theUser.getUsername());
		response.setPassword(theUser.getPassword());
		response.setFirstName(theUser.getFirstName());
		response.setLastName(theUser.getLastName());
		response.setEmail(theUser.getEmail());
		if (theUser.getDateCreated() != null) {
			response.setDateCreated(Date.from(theUser.getDateCreated().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		}
		if (theUser.getDateDeleted() != null) {
			response.setDateDeleted(Date.from(theUser.getDateDeleted().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		}
		
		return response;
	}
	
	private UserDTO mapUser(final UserEntity theUser) {
		UserDTO response = new UserDTO();
		response.setId(theUser.getId());
		response.setUsername(theUser.getUsername());
		response.setPassword(theUser.getPassword());
		response.setFirstName(theUser.getFirstName());
		response.setLastName(theUser.getLastName());
		response.setEmail(theUser.getEmail());
		if (theUser.getDateCreated() != null) {
			response.setDateCreated(LocalDate.from(theUser.getDateCreated().toInstant()));
		}
		if (theUser.getDateDeleted() != null) {
			response.setDateDeleted(LocalDate.from(theUser.getDateDeleted().toInstant()));
		}
		
		return response;
	}

}
