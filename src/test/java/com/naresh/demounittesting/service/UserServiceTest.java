package com.naresh.demounittesting.service;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.naresh.demounittesting.dao.UserDAO;
import com.naresh.demounittesting.dto.UserDTO;
import com.naresh.demounittesting.exception.InvalidLoginException;
import com.naresh.demounittesting.model.User;
import com.naresh.demounittesting.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

	@InjectMocks
	private UserService userService;

	@Mock
	private UserDAO userDAO;

	@Before
	public void setUp() {
			
		 MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testValidLogin() {
		User user1 = new User();
		user1.setEmail("nareshkumarh@live.com");
		user1.setPassword("pass123$");
		Mockito.when(userDAO.login((User) any(User.class))).thenReturn(user1);


		UserDTO userObj = new UserDTO();
		userObj.setEmail("nareshkumarh@live.com");
		userObj.setPassword("pass123");
		User user = userService.login(userObj);
		assertNotNull(user);

	}

	@Test(expected = InvalidLoginException.class)
	public void testInValidLogin() {
		UserDTO userObj = new UserDTO();
		userObj.setEmail("nareshkumarh@live.com");
		userObj.setPassword("pass123");

		Mockito.when(userDAO.login((User) any(User.class))).thenReturn(null);
		User user = userService.login(userObj);

	}

}
