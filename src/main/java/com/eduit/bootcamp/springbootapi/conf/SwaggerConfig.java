package com.eduit.bootcamp.springbootapi.conf;

import org.openapitools.configuration.SpringDocConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
@Import({SpringDocConfiguration.class})
public class SwaggerConfig {



}
