package com.vehicle.service;

import java.util.List;
import java.util.Map;

import com.vehicle.models.Customer;
import com.vehicle.models.Transaction;
import com.vehicle.models.Vehicle;
import com.vehicle.models.enums.VehicleType;

public interface RentalService {
	public void addVehicle(Vehicle vehicle);
	public void addCustomer(Customer customer);
	public void rentVehicle(int customerId, String vehicleId);
	public void returnVehicle(int customerId, String vehicleId);
	public List<Vehicle> availableVehicles();
	public List<Vehicle> top3RentalVehicles();
	public List<Vehicle> searchByModel(String keyword);
	public List<Transaction> getAllTransactons();
	
	
//	Group vehicles by rental count
	public Map<Integer,List<Vehicle>>vehiclesByRentalCount();
	
//		Group vehicles by type
	public Map<VehicleType,List<Vehicle>> groupByVehicles();
	
//		Count total rentals per vehicle type
	public Map<VehicleType,Integer>rentalsPerVehicle();
	

}
