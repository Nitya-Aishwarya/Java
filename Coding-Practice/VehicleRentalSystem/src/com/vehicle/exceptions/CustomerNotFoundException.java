package com.vehicle.exceptions;

//custom exception when customerId not found
public class CustomerNotFoundException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomerNotFoundException() {
		super();
	}

	public CustomerNotFoundException(String message) {
		// TODO Auto-generated constructor stub
		super(message);
	}

}
