package com.hotel.exception;

public class UserNotFoundException extends BookingException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String message) {
        super(message);
    }
}
