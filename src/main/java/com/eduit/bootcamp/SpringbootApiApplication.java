package com.eduit.bootcamp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
public class SpringbootApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApiApplication.class, args);
	}

}
