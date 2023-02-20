package com.eduit.bootcamp.springbootapi.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.eduit.bootcamp.springbootapi.db.repository.UserRepository;
import com.eduit.bootcamp.springbootapi.service.JWTService;
import com.eduit.bootcamp.springbootapi.service.JWTServiceImpl;
import com.eduit.bootcamp.springbootapi.service.UserAuthenticationService;
import com.eduit.bootcamp.springbootapi.service.UserAuthenticationServiceImpl;
import com.eduit.bootcamp.springbootapi.service.UserDetailServiceImpl;
import com.eduit.bootcamp.springbootapi.service.utils.JwtTokenUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableWebSecurity
public class SwaggerWebConfig {

	private static final Logger LOGGER = LoggerFactory.getLogger(SwaggerWebConfig.class);

	@Bean
	public JWTService getJwtService(UserDetailsService userDetailsService, JwtTokenUtil jwtTokenUtil) {
		return new JWTServiceImpl(userDetailsService, jwtTokenUtil);
	}
	
	@Bean
	public UserAuthenticationService getUserAuthenticationService(AuthenticationManager authenticationManager,
			JWTService jwtService) {
		return new UserAuthenticationServiceImpl(authenticationManager, jwtService);
	}
	
	@Bean
	public UserDetailsService getUserDetailService(UserRepository userRepository) {
		return new UserDetailServiceImpl(userRepository);
	}
	
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public JwtTokenUtil getJwtTokenUtil(@Value("${jwt.secret}") String secret) {
    	return new JwtTokenUtil(secret);
    }
    
    @Bean
    public SecurityWebConfig getSecurityWebConfig(ObjectMapper objecMapper, PasswordEncoder encoder, UserDetailsService userDetailsService,
    		JwtTokenUtil jwtTokenUtil, @Value("${api.basePath}") String basePath, @Value("${api.loginPath}") String loginPath,
    		@Value("${api.refreshTokenPath}") String refreshTokenPath) {
    	return new SecurityWebConfig(objecMapper, encoder, userDetailsService, jwtTokenUtil, basePath, loginPath, refreshTokenPath);
    }
    
    @Bean
    public AuthenticationManager getAuthenticationManager(SecurityWebConfig securityWebConfig) throws Exception {
    	return securityWebConfig.authenticationManagerBean();
    }
}
