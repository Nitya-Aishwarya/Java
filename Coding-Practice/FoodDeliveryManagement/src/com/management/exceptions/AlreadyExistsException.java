package com.management.exceptions;

public class AlreadyExistsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AlreadyExistsException() {
		
	}

	public AlreadyExistsException(String message) {
		super(message);
	}
}
