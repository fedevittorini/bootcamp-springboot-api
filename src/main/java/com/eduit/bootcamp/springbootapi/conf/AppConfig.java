package com.eduit.bootcamp.springbootapi.conf;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.eduit.bootcamp.springbootapi.controller.UserController;
import com.eduit.bootcamp.springbootapi.db.repository.UserRepository;
import com.eduit.bootcamp.springbootapi.service.UserAuthenticationService;
import com.eduit.bootcamp.springbootapi.service.UserAuthenticationServiceImpl;

@Configuration
public class AppConfig {


	@Value("${security.secret}")
	private String secretAuth;

	@Autowired
	ApplicationContext context;
	
    @Bean
    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails user = User.withUsername("user")
            .password(passwordEncoder.encode("password"))
            .roles("USER")
            .build();

        UserDetails admin = User.withUsername("admin")
            .password(passwordEncoder.encode("admin"))
            .roles("USER", "ADMIN")
            .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.httpBasic().disable();
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
    	int strength = 10; // work factor of bcrypt
    	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(strength, new SecureRandom());
        return encoder;
    }
	
	@Bean
	public UserController getUserController(UserAuthenticationService userAuthenticationService) {
		return new UserController(userAuthenticationService);
	}
	
	@Bean
	public UserAuthenticationService getUserAuthenticationService(PasswordEncoder encoder,
			UserRepository userRepository) {
		return new UserAuthenticationServiceImpl(secretAuth, encoder, userRepository);
	}
}
