package com.eduit.bootcamp.springbootapi.service;

import java.util.List;
import java.util.Optional;

import com.eduit.bootcamp.springbootapi.model.ProductDTO;
import com.eduit.bootcamp.springbootapi.model.ProductRequestDTO;
import com.eduit.bootcamp.springbootapi.model.UserDTO;
import com.eduit.bootcamp.springbootapi.model.UserRequestDTO;

/**
 * Esta es una capa de negocios para atender los requerimientos del controllador de usuarios.
 * @author Federico Vittorini
 *
 */
public interface UserAdministrationService 
	extends CrudAdministrationService<UserDTO, UserRequestDTO, Long> {

}
