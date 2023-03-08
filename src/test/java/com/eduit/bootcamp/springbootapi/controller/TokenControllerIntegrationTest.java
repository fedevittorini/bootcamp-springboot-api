package com.eduit.bootcamp.springbootapi.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.jdbc.JdbcTestUtils;

import com.eduit.bootcamp.springbootapi.db.entity.UserEntity;
import com.eduit.bootcamp.springbootapi.db.entity.UserRoleEnum;
import com.eduit.bootcamp.springbootapi.db.repository.UserRepository;
import com.eduit.bootcamp.springbootapi.model.ErrorItemDTO;
import com.eduit.bootcamp.springbootapi.model.JWTResponseDTO;
import com.eduit.bootcamp.springbootapi.model.ResponseContainerResponseDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class TokenControllerIntegrationTest {

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
	
	private PasswordEncoder encoder;
	
	private UserRepository userRepository;
	
	private String baseUrl;
	
	@BeforeAll
	public static void setupClass(){

	}
	
	@BeforeEach
	public void setup() throws Exception {
		encoder = context.getBean(PasswordEncoder.class);
		userRepository = context.getBean(UserRepository.class);
		baseUrl = "http://localhost:" + randomServerPort + basePath;
	}
	
	@AfterEach
	public void destroy() {
		JdbcTestUtils.deleteFromTables(jdbcTemplate, UserEntity.TABLE_NAME);
	}

	@Test
	public void testUserLogin_OK() {
		createUser("pedrito", "123", "ROLE_ADMIN");
		
		ResponseEntity<ResponseContainerResponseDTO> response = template.postForEntity(baseUrl + "/token/login?username=pedrito&password=123",
				null, ResponseContainerResponseDTO.class);
		assertNotNull(response);
		ResponseContainerResponseDTO entity = response.getBody();
		assertNull(entity.getErrors());
		
		JWTResponseDTO data = (JWTResponseDTO) entity.getData();
		assertNotNull(data);
		assertNotNull(data.getAccessToken());
		assertNotNull(data.getRefreshToken());
	}
	
	@Test
	public void testUserLogin_invalidPasswordOK() {
		createUser("pedrito", "888");
		
		ResponseEntity<ResponseContainerResponseDTO> response = template.postForEntity(baseUrl + "/token/login?username=pedrito&password=123",
				null, ResponseContainerResponseDTO.class);
		assertNotNull(response);
		ResponseContainerResponseDTO entity = response.getBody();
		List<ErrorItemDTO> errorList = entity.getErrors();
		assertEquals(errorList.size(), 1);
		ErrorItemDTO error = errorList.get(0);
		assertNotNull(error);
		assertEquals(error.getDetail(), "The passwords doesn't match");
		assertNull(entity.getData());
	}
	
	@Test
	public void testUserLogin_invalidUserOK() {
		createUser("martin", "888", "ROLE_ADMIN", false);
		
		ResponseEntity<ResponseContainerResponseDTO> response = template.postForEntity(baseUrl + "/token/login?username=pedrito&password=123",
				null, ResponseContainerResponseDTO.class);
		assertNotNull(response);
		ResponseContainerResponseDTO entity = response.getBody();
		List<ErrorItemDTO> errorList = entity.getErrors();
		assertEquals(errorList.size(), 1);
		ErrorItemDTO error = errorList.get(0);
		assertNotNull(error);
		assertEquals(error.getDetail(), "User not found");
		assertNull(entity.getData());
	}
	
	private void createUser(String username, String password) {
		createUser(username, password, "ROLE_ADMIN");
	}
	
	private void createUser(String username, String password, String roleName) {
		createUser(username, password, "ROLE_ADMIN", true);
	}
	
	private void createUser(String username, String password, String roleName, Boolean hasRole) {
		UserEntity user = new UserEntity();
		user.setEmail("test@test.com");
		user.setFirstName("Pedro");
		user.setLastName("Gomez");
		user.setUsername(username);
		if (hasRole) {
			user.setRole(UserRoleEnum.valueOf(roleName));
		}
		user.setPassword(encoder.encode(password));
		user.setDateCreated(new Date());
		userRepository.save(user);
	}
}
