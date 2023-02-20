package com.eduit.bootcamp.springbootapi.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.eduit.bootcamp.springbootapi.api.TokenApiDelegate;
import com.eduit.bootcamp.springbootapi.model.ResponseContainerUserResponseDTO;
import com.eduit.bootcamp.springbootapi.service.JWTService;
import com.eduit.bootcamp.springbootapi.service.UserAuthenticationService;

public class TokenController implements TokenApiDelegate {

	private static final Logger LOGGER = LoggerFactory.getLogger(TokenController.class);

	private final UserAuthenticationService userAuthenticationService;
	private final JWTService jwtService;
	
	public TokenController(UserAuthenticationService theUserAuthenticationService,
			JWTService theJwtService) {
		userAuthenticationService = theUserAuthenticationService;
		jwtService = theJwtService;
	}

	public ResponseEntity<ResponseContainerUserResponseDTO> login(String username,
	        String password) {
		LOGGER.debug("refreshToken");
		try {
			Map<String, String> tokens = userAuthenticationService.login(username, password);
			HttpHeaders headers = new HttpHeaders();
			tokens.forEach((k, v) -> headers.add(k, v));
			return ResponseEntity.status(HttpStatus.ACCEPTED).headers(headers).build();
		} catch (Exception e) {
			LOGGER.error(String.format("Login failed for user: \"%s\" pwd: \"%s\" ", username, password), e);
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}
	
	public ResponseEntity<ResponseContainerUserResponseDTO> logout(String authorization) {
		LOGGER.debug("refreshToken");
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}

	public ResponseEntity<ResponseContainerUserResponseDTO> refreshToken(String authorization) {
		LOGGER.debug("refreshToken");
		Map<String, String> tokens = jwtService.validateRefreshToken(authorization);
		HttpHeaders headers = new HttpHeaders();
		tokens.forEach((k, v) -> headers.add(k, v));
		return ResponseEntity.status(HttpStatus.ACCEPTED).headers(headers).build();
	}
	
}
