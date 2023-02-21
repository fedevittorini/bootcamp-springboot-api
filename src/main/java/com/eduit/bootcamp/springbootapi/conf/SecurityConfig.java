package com.eduit.bootcamp.springbootapi.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.eduit.bootcamp.springbootapi.controller.ProductController;
import com.eduit.bootcamp.springbootapi.controller.TokenController;
import com.eduit.bootcamp.springbootapi.controller.UserController;
import com.eduit.bootcamp.springbootapi.db.repository.UserRepository;
import com.eduit.bootcamp.springbootapi.security.filter.CustomAuthorizationFilter;
import com.eduit.bootcamp.springbootapi.service.JWTService;
import com.eduit.bootcamp.springbootapi.service.JWTServiceImpl;
import com.eduit.bootcamp.springbootapi.service.UserAdministrationService;
import com.eduit.bootcamp.springbootapi.service.UserAuthenticationService;
import com.eduit.bootcamp.springbootapi.service.UserAuthenticationServiceImpl;
import com.eduit.bootcamp.springbootapi.service.UserDetailServiceImpl;
import com.eduit.bootcamp.springbootapi.service.UserMapper;
import com.eduit.bootcamp.springbootapi.service.UserMapperImpl;
import com.eduit.bootcamp.springbootapi.service.utils.JwtTokenUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private static final Logger LOGGER = LoggerFactory.getLogger(SecurityConfig.class);
    
    @Bean
    public JwtTokenUtil getJwtTokenUtil(@Value("${jwt.secret}") String secret) {
    	return new JwtTokenUtil(secret);
    }
   
    @Bean
    public CustomAuthorizationFilter getCustomAuthorizationFilter(ObjectMapper objectMapper, PasswordEncoder encoder, 
    		UserDetailsService userDetailsService, JwtTokenUtil jwtTokenUtil,
    		@Value("${api.basePath}") String basePath, @Value("${api.loginPath}") String loginPath,
    		@Value("${api.refreshTokenPath}") String refreshTokenPath){
    	return new CustomAuthorizationFilter(objectMapper, userDetailsService, jwtTokenUtil, basePath, loginPath, 
    			refreshTokenPath);
    }
    
    @Bean
    public SecurityFilterChain getSecurityFilterChain(HttpSecurity http, CustomAuthorizationFilter customAuthorizationFilter,
    		@Value("${api.basePath}") String basePath) throws Exception {
    	http.csrf().disable();
        http.formLogin().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers(basePath + "/users/**").hasAnyAuthority("ROLE_USER");
        http.authorizeRequests().antMatchers(basePath + "/products/**").hasAnyAuthority("ROLE_ADMIN");
        http.authorizeRequests().anyRequest().permitAll();
        http.addFilterBefore(customAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
    	return http.build();
    }
}