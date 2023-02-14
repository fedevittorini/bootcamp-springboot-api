package com.eduit.bootcamp.springbootapi.conf;

import org.openapitools.configuration.SpringDocConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(SpringDocConfiguration.class)
public class SwaggerConfig {


}
