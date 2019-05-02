package com.naresh.demounittesting.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.naresh.demounittesting.dto.UserDTO;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class WebLayerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testLogin() throws Exception {
		UserDTO userObj = new UserDTO();

		userObj.setEmail("nareshkumarh@live.com");
		userObj.setPassword("pass123$");


		String userJson = new ObjectMapper().writeValueAsString(userObj);
		mockMvc.perform(post("/auth/login").contentType(MediaType.APPLICATION_JSON).content(userJson))
				.andExpect(status().isOk()).
				andExpect(jsonPath("$.email").value("nareshkumarh@live.com")).
				andExpect(jsonPath("$.name").value("naresh"));

	}

	@Test
	public void testInvalidLogin() throws Exception {
		UserDTO userObj = new UserDTO();
		userObj.setEmail("nareshkumarh@live.com");
		userObj.setPassword("pass123");

		String userJson = new ObjectMapper().writeValueAsString(userObj);
		mockMvc.perform(post("/auth/login").contentType(MediaType.APPLICATION_JSON).content(userJson))
				.andExpect(status().isUnauthorized()).
				andExpect(jsonPath("$.errorMessage").value("Invalid email/password"));

	}

}
