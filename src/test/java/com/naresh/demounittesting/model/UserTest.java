package com.naresh.demounittesting.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class UserTest {
	
	@Test
	public void testUser() {
		User user = new User();
		user.setId(1);
		user.setName("naresh");
		user.setEmail("nareshkumarh@live.com");
		user.setPassword("pass123$");
		assertEquals(user.getId(), 1);
		assertEquals(user.getName(), "naresh");
		assertEquals(user.getEmail(), "nareshkumarh@live.com");
		assertEquals(user.getPassword(), "pass123$");
	}
}
