package com.naresh.demounittesting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.naresh.demounittesting.dto.ErrorDto;
import com.naresh.demounittesting.dto.UserDTO;
import com.naresh.demounittesting.model.User;
import com.naresh.demounittesting.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping("auth")
@Api(value = "Authentication")
public class AuthController {

	@Autowired
	private UserService userService;

	@ApiOperation(value = "Authenticate User", response = User.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "User Login Success", response = User.class),
			@ApiResponse(code = 401, message = "Invalid Login Credentials", response = ErrorDto.class) })
	@PostMapping("login")
	public ResponseEntity<?> login(@RequestBody UserDTO userDto) {

		System.out.println("Authcontroller:" + userDto);

		User user = userService.login(userDto);
		return new ResponseEntity<>(user, HttpStatus.OK);

	}
}
