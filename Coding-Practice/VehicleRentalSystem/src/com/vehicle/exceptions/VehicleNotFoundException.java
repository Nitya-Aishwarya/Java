package com.vehicle.exceptions;

//custom exception when vehicleId not found
public class VehicleNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VehicleNotFoundException() {
		super();
	}

	public VehicleNotFoundException(String message) {
		super(message);
	}

}
