package com.hotel.exception;

public class DateInvalidException extends BookingException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DateInvalidException(String message) {
        super(message);
    }
}
