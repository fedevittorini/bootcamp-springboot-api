package com.eduit.bootcamp.springbootapi.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.eduit.bootcamp.springbootapi.db.repository.UserRepository;
import com.eduit.bootcamp.springbootapi.security.filter.CustomAuthorizationFilter;
import com.eduit.bootcamp.springbootapi.service.JWTService;
import com.eduit.bootcamp.springbootapi.service.JWTServiceImpl;
import com.eduit.bootcamp.springbootapi.service.UserAuthenticationService;
import com.eduit.bootcamp.springbootapi.service.UserAuthenticationServiceImpl;
import com.eduit.bootcamp.springbootapi.service.utils.JwtTokenUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SecurityWebConfig extends WebSecurityConfigurerAdapter {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(SecurityWebConfig.class);
	
	private ObjectMapper objectMapper;
	
	private PasswordEncoder encoder;
	
	private UserDetailsService userDetailsService;
	
	private JwtTokenUtil jwtTokenUtil;
	
	private String basePath;
	private String loginPath;
	private String refreshTokenPath;
	
	public SecurityWebConfig(ObjectMapper theObjectMapper, PasswordEncoder theEncoder, UserDetailsService userDetailsService, JwtTokenUtil jwtTokenUtil,
			final String theBasePath, final String theLoginPath, final String theRefreshTokenPath) {
		this.objectMapper = theObjectMapper;
		this.encoder = theEncoder;
		this.userDetailsService = userDetailsService;
		this.jwtTokenUtil = jwtTokenUtil;
		this.basePath = theBasePath;
		this.loginPath = theLoginPath;
		this.refreshTokenPath = theRefreshTokenPath;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers(basePath + "/users/**").hasAnyAuthority("ROLE_USER");
        http.authorizeRequests().antMatchers(basePath + "/products/**").hasAnyAuthority("ROLE_ADMIN");
        http.authorizeRequests().anyRequest().permitAll();
        http.addFilterBefore(
        	new CustomAuthorizationFilter(objectMapper, userDetailsService, jwtTokenUtil, basePath, loginPath, 
        			refreshTokenPath), UsernamePasswordAuthenticationFilter.class);
	}

}
