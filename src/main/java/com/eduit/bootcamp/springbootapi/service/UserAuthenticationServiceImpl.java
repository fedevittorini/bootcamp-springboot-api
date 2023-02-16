package com.eduit.bootcamp.springbootapi.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.eduit.bootcamp.springbootapi.db.entity.UserEntity;
import com.eduit.bootcamp.springbootapi.db.repository.UserRepository;

public class UserAuthenticationServiceImpl implements UserAuthenticationService {

	private UserRepository userRepository;

	private PasswordEncoder encoder;
	
	public UserAuthenticationServiceImpl(final String theSecret,
			PasswordEncoder theEncoder,
			UserRepository theUserRepository) {
		userRepository = theUserRepository;
		encoder = theEncoder;
	}

	@Override
	public String login(String username, String password) {
		Optional<UserEntity> opUsr = userRepository.findOneByUsername(username);
		if (opUsr.isEmpty()) {
			throw new RuntimeException("User not found");
		}
		UserEntity ret = opUsr.get();
		System.out.print(encoder.encode(password));
		if (encoder.matches(password, ret.getPassword())) {
			return ret.getUsername();
		}	
		
		throw new RuntimeException("password missmatch");
	}

}
