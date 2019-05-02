package com.naresh.demounittesting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naresh.demounittesting.dao.UserDAO;
import com.naresh.demounittesting.dto.UserDTO;
import com.naresh.demounittesting.exception.InvalidLoginException;
import com.naresh.demounittesting.model.User;

@Service
public class UserService {

	@Autowired
	private UserDAO userDAO;
	
	public User login(UserDTO userDTO)  {
		System.out.println("UserService -> login");
		User user = new User();
		user.setEmail(userDTO.getEmail());
		user.setPassword(userDTO.getPassword());

		User userObj = userDAO.login(user);
		System.out.println(userObj);
		if( userObj ==null) {
			throw new InvalidLoginException("Invalid email/password");
		}
		return userObj;
		
	}

}
