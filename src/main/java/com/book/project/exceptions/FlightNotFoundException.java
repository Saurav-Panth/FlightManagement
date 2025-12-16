package com.book.project.exceptions;

public class FlightNotFoundException extends RuntimeException{
	public FlightNotFoundException(String message) {
		super(message);
	}
}
