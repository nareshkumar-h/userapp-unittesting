package com.naresh.demounittesting.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.naresh.demounittesting.dto.UserDTO;
import com.naresh.demounittesting.exception.InvalidLoginException;
import com.naresh.demounittesting.model.User;
import com.naresh.demounittesting.service.UserService;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class AuthControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userServiceMock;

	@InjectMocks
	AuthController authController;

	@Before
	public void setUp() {

		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testLogin() throws Exception {
		User userObj = new User();

		userObj.setEmail("nareshkumarh@live.com");
		userObj.setPassword("pass123$");
		when(userServiceMock.login(any(UserDTO.class))).thenReturn(userObj);


		String userJson = new ObjectMapper().writeValueAsString(userObj);
		mockMvc.perform(post("/auth/login").contentType(MediaType.APPLICATION_JSON).content(userJson))
				.andExpect(status().isOk()).andExpect(jsonPath("$.email").value("nareshkumarh@live.com"));

	}

	@Test
	public void testInvalidLogin() throws Exception {
		User userObj = new User();
		userObj.setEmail("nareshkumarh@live.com");
		userObj.setPassword("pass123");

		when(userServiceMock.login(any(UserDTO.class)))
				.thenThrow(new InvalidLoginException("Invalid login credentials"));

		String userJson = new ObjectMapper().writeValueAsString(userObj);

		mockMvc.perform(post("/auth/login").contentType(MediaType.APPLICATION_JSON).content(userJson))
				.andExpect(status().isUnauthorized())
				.andExpect(jsonPath("$.errorMessage").value("Invalid login credentials"));

	}

}
