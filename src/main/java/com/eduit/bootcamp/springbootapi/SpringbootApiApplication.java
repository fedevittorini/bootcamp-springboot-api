package com.eduit.bootcamp.springbootapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import com.eduit.bootcamp.springbootapi.conf.AppConfig;
import com.eduit.bootcamp.springbootapi.conf.SwaggerConfig;

@SpringBootApplication
@ComponentScan(basePackages = {"com.eduit.bootcamp.springbootapi.api", "com.eduit.bootcamp.springbootapi.controller"})
@Import({AppConfig.class, SwaggerConfig.class})
public class SpringbootApiApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(SpringbootApiApplication.class, args);
	}
	public SpringbootApiApplication(){}
	public void mm() {}
}
