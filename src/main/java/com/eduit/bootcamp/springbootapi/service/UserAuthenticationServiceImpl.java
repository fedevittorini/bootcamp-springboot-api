package com.eduit.bootcamp.springbootapi.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.eduit.bootcamp.springbootapi.db.entity.UserEntity;
import com.eduit.bootcamp.springbootapi.db.repository.UserRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class UserAuthenticationServiceImpl implements UserAuthenticationService {

	private String secretKey;
	
	private UserRepository userRepository;

	private PasswordEncoder encoder;
	
	public UserAuthenticationServiceImpl(final String theSecret,
			PasswordEncoder theEncoder,
			UserRepository theUserRepository) {
		secretKey = theSecret;
		userRepository = theUserRepository;
		encoder = theEncoder;
	}

	@Override
	public String getToken(Long id, String username) {
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_USER");
		
		String token = Jwts
				.builder()
				.setId(String.valueOf(id))
				.setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512,
						secretKey.getBytes()).compact();

		return "Bearer " + token;
	}

	@Override
	public UserEntity login(String username, String password) {
		Optional<UserEntity> opUsr = userRepository.findOneByUsername(username);
		if (opUsr.isEmpty()) {
			throw new RuntimeException("User not found");
		}
		UserEntity ret = opUsr.get();
		System.out.print(encoder.encode(password));
		if (encoder.matches(password, ret.getPassword())) {
			return ret;
		}	
		
		throw new RuntimeException("password missmatch");
	}

}
