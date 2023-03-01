package com.eduit.bootcamp.springbootapi.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.eduit.bootcamp.springbootapi.db.entity.UserEntity;
import com.eduit.bootcamp.springbootapi.model.UserDTO;
import com.eduit.bootcamp.springbootapi.utils.DateUtils;

@RunWith(JUnit4.class)
public class UserMapperTest {

	private UserMapper target;
	
	private PasswordEncoder encoder;
	
	@BeforeEach
	public void setupClass() {
		encoder = mock(BCryptPasswordEncoder.class);
		target = new UserMapperImpl(encoder);
	}
	
	@Test
	public void testMapUserUserDTO_allFieldOK() {
		UserDTO dto = new UserDTO();
		dto.setUsername("userTest");
		dto.setFirstName("fName");
		dto.setLastName("lName");
		dto.setEmail("test@demo.com");
		dto.setPassword("123");
		dto.setDateCreated(LocalDate.now());
		
		UserEntity response = target.mapUser(dto);
		
		assertNotNull(response);
		assertEquals(response.getUsername(), dto.getUsername());
		assertEquals(response.getFirstName(), dto.getFirstName());
		assertEquals(response.getLastName(), dto.getLastName());
		assertEquals(response.getPassword(), dto.getPassword());
		assertEquals(response.getId(), dto.getId());
		assertEquals(response.getEmail(), dto.getEmail());
		assertEquals(DateUtils.toLocalDate(response.getDateCreated()), dto.getDateCreated());
		assertEquals(response.getDateDeleted(), null);
	}
	
	@Test
	public void testMapUserEncodedUserDTO_allFieldOK() {
		String pwd = "123";
		String encPwd = "TTTTTTTTT";
		UserDTO dto = new UserDTO();
		dto.setUsername("userTest");
		dto.setFirstName("fName");
		dto.setLastName("lName");
		dto.setEmail("test@demo.com");
		dto.setPassword(pwd);
		dto.setDateCreated(LocalDate.now());
		
		when(encoder.encode(eq(pwd))).thenReturn(encPwd);
		
		UserEntity response = target.mapUserEncoded(dto);
		
		assertNotNull(response);
		assertEquals(response.getUsername(), dto.getUsername());
		assertEquals(response.getFirstName(), dto.getFirstName());
		assertEquals(response.getLastName(), dto.getLastName());
		assertEquals(response.getPassword(), encPwd);
		assertEquals(response.getId(), dto.getId());
		assertEquals(response.getEmail(), dto.getEmail());
		assertEquals(DateUtils.toLocalDate(response.getDateCreated()), dto.getDateCreated());
		assertEquals(response.getDateDeleted(), null);
	}
	
	@Test
	public void testMapUserEncodedUserDTO_missingPasswordERROR() {
		UserDTO dto = new UserDTO();
		dto.setUsername("userTest");
		dto.setFirstName("fName");
		dto.setLastName("lName");
		dto.setEmail("test@demo.com");
		dto.setDateCreated(LocalDate.now());
		
		when(encoder.encode(eq(null))).thenReturn(null);
		
		Exception retrievedEx = null;
		try {
			target.mapUserEncoded(dto);
		} catch (Exception e) {
			retrievedEx = e;
		} 
		assertNotNull(retrievedEx);
		assertEquals(retrievedEx.getClass(), NullPointerException.class);
		assertEquals(retrievedEx.getMessage(), "The password cannot be null or blank");	
	}
}
