package com.eduit.bootcamp.springbootapi.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.eduit.bootcamp.springbootapi.model.JWTResponseDTO;
import com.eduit.bootcamp.springbootapi.model.ResponseContainerResponseDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class DefaulLoginIntegrationTest {

	@Autowired
    private ApplicationContext context;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private TestRestTemplate template;
	
    @LocalServerPort
    private int randomServerPort;
    
    @Value("${api.basePath}") 
    private String basePath;
	
	private String baseUrl;
	
	@BeforeAll
	public static void setupClass(){

	}
	
	@BeforeEach
	public void setup() throws Exception {
		baseUrl = "http://localhost:" + randomServerPort + basePath;
	}

	@Test
	public void testUserLogin_OK() {
		
		ResponseEntity<ResponseContainerResponseDTO> response = template.postForEntity(baseUrl + "/token/login?username=admin&password=admin",
				null, ResponseContainerResponseDTO.class);
		assertNotNull(response);
		ResponseContainerResponseDTO entity = response.getBody();
		assertNull(entity.getErrors());
		
		JWTResponseDTO data = (JWTResponseDTO) entity.getData();
		assertNotNull(data);
		assertNotNull(data.getAccessToken());
		assertNotNull(data.getRefreshToken());
	}
	
}
