package com.eduit.bootcamp.springbootapi.service;

import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;

public class UserAuthenticationServiceImpl implements UserAuthenticationService {

	private JWTService jwtService;
	private AuthenticationManager authenticationManager;

		
	public UserAuthenticationServiceImpl(AuthenticationManager theAuthenticationManager, JWTService theJwtService) {
		authenticationManager = theAuthenticationManager;
		jwtService = theJwtService;
	}


	@Override
	public Map<String, String> login(String username, String password) {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
		Authentication authResult = authenticationManager.authenticate(authenticationToken);
		User user = (User) authResult.getPrincipal();
		Map<String, String> tokens = jwtService.generateTokenSet(user);
		return tokens;
	}

}
