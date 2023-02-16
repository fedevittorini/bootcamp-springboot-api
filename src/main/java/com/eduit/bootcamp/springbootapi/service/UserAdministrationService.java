package com.eduit.bootcamp.springbootapi.service;

import java.util.List;

import com.eduit.bootcamp.springbootapi.model.UserDTO;

/**
 * Esta es una capa de negocios para atender los requerimientos del controllador de usuarios.
 * @author Federico Vittorini
 *
 */
public interface UserAdministrationService {

	UserDTO createUser(final UserDTO theUser);
	
	List<UserDTO> listUsers();
}
