package com.eduit.bootcamp.springbootapi.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.eduit.bootcamp.springbootapi.controller.ProductController;
import com.eduit.bootcamp.springbootapi.controller.TokenController;
import com.eduit.bootcamp.springbootapi.controller.UserController;
import com.eduit.bootcamp.springbootapi.db.repository.UserRepository;
import com.eduit.bootcamp.springbootapi.service.JWTService;
import com.eduit.bootcamp.springbootapi.service.UserAdministrationService;
import com.eduit.bootcamp.springbootapi.service.UserAdministrationServiceImpl;
import com.eduit.bootcamp.springbootapi.service.UserAuthenticationService;
import com.eduit.bootcamp.springbootapi.service.UserMapper;
import com.eduit.bootcamp.springbootapi.service.UserMapperImpl;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Configuration
public class AppConfig {

	@Bean
	public UserController getUserController(UserAdministrationService userAdministrationService) {
		return new UserController(userAdministrationService);
	}
	
	@Bean
	public TokenController getTokenController(UserAuthenticationService userAuthenticationService, 
			JWTService jwtService) {
		return new TokenController(userAuthenticationService, jwtService);
	}
	
	@Bean
	public ProductController getProductController() {
		return new ProductController();
	}
	
	@Bean
	public UserMapper getUserMapper(PasswordEncoder encoder) {
		return new UserMapperImpl(encoder);
	}
	
	@Bean
	public UserAdministrationService getUserAdministrationService(UserMapper userMapper, 
			UserRepository userRepository) {
		return new UserAdministrationServiceImpl(userMapper, userRepository);
	}

}
