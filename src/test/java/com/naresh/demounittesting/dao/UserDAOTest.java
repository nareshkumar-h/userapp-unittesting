package com.naresh.demounittesting.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.naresh.demounittesting.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDAOTest {

	@Autowired
	private UserDAO userDAO;

	@Test
	public void testValidLogin() {

		User userObj = new User();
		userObj.setEmail("nareshkumarh@live.com");
		userObj.setPassword("pass123$");
		User user = userDAO.login(userObj);
		assertNotNull(user);

	}

	@Test
	public void testInValidLogin() {
		User userObj = new User();
		userObj.setEmail("nareshkumarh@live.com");
		userObj.setPassword("pass123");
		User user = userDAO.login(userObj);
		assertNull(user);

	}

}
