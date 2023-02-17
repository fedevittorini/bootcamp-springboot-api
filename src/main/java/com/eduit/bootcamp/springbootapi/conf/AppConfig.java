package com.eduit.bootcamp.springbootapi.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.eduit.bootcamp.springbootapi.controller.ProductController;
import com.eduit.bootcamp.springbootapi.controller.UserController;
import com.eduit.bootcamp.springbootapi.db.repository.UserRepository;
import com.eduit.bootcamp.springbootapi.service.UserAdministrationService;
import com.eduit.bootcamp.springbootapi.service.UserAdministrationServiceImpl;
import com.eduit.bootcamp.springbootapi.service.UserAuthenticationService;
import com.eduit.bootcamp.springbootapi.service.UserAuthenticationServiceImpl;
import com.eduit.bootcamp.springbootapi.service.UserMapper;
import com.eduit.bootcamp.springbootapi.service.UserMapperImpl;

@Configuration
public class AppConfig {


	@Value("${jwt.secret}")
	private String secretAuth;

	@Autowired
	ApplicationContext context;
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.httpBasic().disable();
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
	@Bean
	public UserController getUserController(UserAuthenticationService userAuthenticationService, 
			UserAdministrationService userAdministrationService) {
		return new UserController(userAdministrationService, userAuthenticationService);
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
	
	@Bean
	public UserAuthenticationService getUserAuthenticationService(PasswordEncoder encoder,
			UserRepository userRepository) {
		return new UserAuthenticationServiceImpl(secretAuth, encoder, userRepository);
	}
}
