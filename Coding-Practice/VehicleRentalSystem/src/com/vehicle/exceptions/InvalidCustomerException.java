package com.vehicle.exceptions;

//custom exception when invalid name/phone
public class InvalidCustomerException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidCustomerException() {
		// TODO Auto-generated constructor stub
	}

	public InvalidCustomerException(String message) {
		super(message);
	}
	
}
