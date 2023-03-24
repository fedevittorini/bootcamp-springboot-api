package com.eduit.bootcamp.springbootapi.service.mapper;

import com.eduit.bootcamp.springbootapi.db.entity.UserEntity;
import com.eduit.bootcamp.springbootapi.model.UserDTO;
import com.eduit.bootcamp.springbootapi.model.UserRequestDTO;

public interface UserMapper {

	/**
	 * This method create a UserEntity from given UserDTO.
	 * 
	 * @param theUser the User DTO to extract data. It cannot be null.
	 * @return The new user entity. Never null.
	 */
	UserEntity map(final UserDTO theUser);
	
	/**
	 * This method create a UserEntity from given UserDTO.
	 * This also encrypt the password to the destination entity.
	 * 
	 * @param theUser the User DTO to extract data. It cannot be null.
	 * @return The new user entity. Never null.
	 */
	UserEntity mapEncoded(final UserDTO theUser);
	
	/**
	 * This method create a UserEntity from given UserDTO.
	 * This also encrypt the password to the destination entity.
	 * 
	 * @param theUser the User DTO to extract data. It cannot be null.
	 * @return The new user entity. Never null.
	 */
	UserEntity mapEncoded(final UserRequestDTO theUser);
	
	UserDTO map(final UserEntity theUser);
	
	UserEntity fill(final UserDTO source, final UserEntity target);
}
