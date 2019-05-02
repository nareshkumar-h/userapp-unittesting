package com.naresh.demounittesting.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.naresh.demounittesting.dto.ErrorDto;
import com.naresh.demounittesting.exception.InvalidLoginException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { InvalidLoginException.class })
	protected ResponseEntity<Object> handleConflict(InvalidLoginException ex, WebRequest request) {

		ErrorDto error = new ErrorDto();
		error.setErrorMessage(ex.getMessage());
		return new ResponseEntity<Object>(error, HttpStatus.UNAUTHORIZED);
	}
}