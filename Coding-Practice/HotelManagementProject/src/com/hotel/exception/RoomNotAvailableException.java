package com.hotel.exception;

public class RoomNotAvailableException extends BookingException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RoomNotAvailableException(String message) {
        super(message);
    }
}
