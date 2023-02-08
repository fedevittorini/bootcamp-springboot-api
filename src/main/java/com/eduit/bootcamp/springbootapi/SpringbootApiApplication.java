package com.eduit.bootcamp.springbootapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import com.eduit.bootcamp.springbootapi.conf.AppConfig;

@SpringBootApplication
@EnableAutoConfiguration
@Import(AppConfig.class)
@ComponentScan(basePackages = {"com.eduit.bootcamp.springbootapi.controller"})
public class SpringbootApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApiApplication.class, args);
	}

}
