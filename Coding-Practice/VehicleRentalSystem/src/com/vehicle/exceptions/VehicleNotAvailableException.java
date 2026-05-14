package com.vehicle.exceptions;

//custom exception when renting an already-rented vehicle
public class VehicleNotAvailableException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VehicleNotAvailableException() {
		super();
	}

	public VehicleNotAvailableException(String message) {
		super(message);
	}

}
