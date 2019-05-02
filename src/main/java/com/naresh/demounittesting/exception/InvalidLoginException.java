package com.naresh.demounittesting.exception;

@SuppressWarnings("serial")
public class InvalidLoginException extends RuntimeException{

	public InvalidLoginException(String message) {
		super(message);
	}
}
