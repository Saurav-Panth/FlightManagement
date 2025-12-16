package com.book.project.exceptions;


public class PassengerNotFoundException extends RuntimeException{
	public PassengerNotFoundException(String message) {
		super(message);
	}
}
