package com.eduit.bootcamp.springbootapi.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.eduit.bootcamp.springbootapi.db.entity.UserEntity;
import com.eduit.bootcamp.springbootapi.db.repository.UserRepository;
import com.eduit.bootcamp.springbootapi.service.utils.JwtTokenUtil;

public class UserDetailServiceImpl implements UserDetailsService {

	private UserRepository userRepository;
		
	public UserDetailServiceImpl(UserRepository theUserRepository) {
		userRepository = theUserRepository;
	}

	/**
	 * Required to check authorities
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = findByUsername(username);
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		user.getRoles().forEach(role -> {
			SimpleGrantedAuthority auth = new SimpleGrantedAuthority(role.getName());
			authorities.add(auth);
		});
		UserDetails userDetails = new User(user.getUsername(), user.getPassword(), authorities);
		return userDetails;
	}
	
	private UserEntity findByUsername(final String username) {
		Optional<UserEntity> opUsr = userRepository.findOneByUsername(username);
		if (opUsr.isEmpty()) {
			throw new RuntimeException("User not found");
		}
		return opUsr.get();
	}
}
