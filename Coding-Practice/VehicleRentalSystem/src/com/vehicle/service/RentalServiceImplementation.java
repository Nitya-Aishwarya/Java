package com.vehicle.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.vehicle.exceptions.AlreadyExistsException;
import com.vehicle.exceptions.CustomerNotFoundException;
import com.vehicle.exceptions.InvalidVehicleException;
import com.vehicle.exceptions.VehicleNotAvailableException;
import com.vehicle.exceptions.VehicleNotFoundException;
import com.vehicle.interfaces.TimeTracker;
import com.vehicle.models.Customer;
import com.vehicle.models.Transaction;
import com.vehicle.models.Vehicle;
import com.vehicle.models.enums.TransactionType;
import com.vehicle.models.enums.VehicleType;

public class RentalServiceImplementation implements RentalService,TimeTracker {

	private final List<Vehicle>vehicles=new ArrayList<Vehicle>();
	private final List<Customer>customers=new ArrayList<Customer>();
	private final List<Transaction>transactions=new ArrayList<Transaction>();
//	private final List<Integer>rentalCounts=new ArrayList<Integer>();
	private final Map<Vehicle, Integer> rentalCounts=new HashMap<Vehicle, Integer>();
	private int vehicleCount=0;
	private int customerCount=0;
	private int transactionCount=0;
	private int rentalCount=0;
	
	public int getRentalCount() {
		return rentalCount;
	}
	public int getVehicleCount() {
		return vehicleCount;
	}

	public int getCustomerCount() {
		return customerCount;
	}

	public int getTransactionCount() {
		return transactionCount;
	}

	@Override
	public void addVehicle(Vehicle vehicle) {
		// TODO Auto-generated method stub
		if(vehicles.stream().anyMatch(veh->veh.equals(vehicle))) {
			throw new AlreadyExistsException(vehicle+" vehicle already exists!");
		}
		
		vehicles.add(vehicle);
		vehicleCount++;
		System.out.println("Vehicle added successfully");
		
	}

	@Override
	public void addCustomer(Customer customer) {
		// TODO Auto-generated method stub
		if(customers.stream().anyMatch(cus->cus.equals(customer))) {
			throw new AlreadyExistsException(customer+ " already exists");
		}
		customers.add(customer);
		customerCount++;
		System.out.println("Customer added successfully");
	}

	@Override
	public void rentVehicle(int customerId, String vehicleId) {
		// TODO Auto-generated method stub
		customers.stream()
				.filter(cus->cus.getCustomerId()==customerId)
				.findFirst()
				.orElseThrow(()->new CustomerNotFoundException("Customer not found"));		
		
		
		Vehicle vehicle=vehicles.stream()
								.filter(veh->veh.getVehicleId().equals(vehicleId))
								.findFirst()
								.orElseThrow(()->new VehicleNotFoundException("Vehicle not found"));
	
		if(!vehicle.isAvailable()) {
			throw new VehicleNotAvailableException("Vehicle not available");
		}
		
		Transaction rentTransaction=new Transaction(vehicleId, customerId, TransactionType.RENT);
		transactions.add(rentTransaction);
		vehicle.setAvailable(false);
		
		transactionCount++;
		
		rentalCounts.put(vehicle, rentalCounts.getOrDefault(vehicle,0)+1);

		
		System.out.println("Vehicle rented by the customer successfully");
	}

	
	@Override
	public void returnVehicle(int customerId, String vehicleId) {
		// TODO Auto-generated method stub
		Vehicle vehicle=vehicles.stream()
				.filter(veh->veh.getVehicleId().equals(vehicleId))
				.findFirst()
				.orElseThrow(()->new VehicleNotFoundException("Vehicle not found"));
		
		if(vehicle.isAvailable())
		{
			throw new InvalidVehicleException("Vehicle is not rented yet");
		}
			
		long rentalCount=transactions.stream()
				.filter(transaction->transaction.getCustomerId()==customerId && transaction.getVehicleId().equals(vehicleId))
				.filter(transaction->transaction.getType()==TransactionType.RENT).count();
		
		long returnCount=transactions.stream()
				.filter(transaction->transaction.getCustomerId()==customerId && transaction.getVehicleId().equals(vehicleId))
				.filter(transaction->transaction.getType()==TransactionType.RETURN).count();
		
		if(rentalCount==returnCount) {
			throw new InvalidVehicleException("No vehicle to be returned by the customer");
		}
		
		
		Transaction returnTransaction=new Transaction(vehicleId, customerId, TransactionType.RETURN);
		transactions.add(returnTransaction);
		transactionCount++;
		vehicle.setAvailable(true);
	}

	@Override
	public List<Vehicle> availableVehicles() {
		// TODO Auto-generated method stub
		List<Vehicle> availableVehicles= vehicles.stream().filter(Vehicle::isAvailable).toList();
		if(availableVehicles.isEmpty()) {
			throw new VehicleNotAvailableException("No vehicles are available");
		}
		return availableVehicles;
	}
	@Override
	public List<Vehicle> top3RentalVehicles() {
		// TODO Auto-generated method stub
		List<Integer>top3Counts=rentalCounts.values().stream()
													.sorted(Comparator.reverseOrder())
													.distinct().limit(3).toList();
		return rentalCounts.entrySet().stream()
								.filter(entry->top3Counts.contains(entry.getValue()))
								.map(Map.Entry::getKey)
								.sorted()
								.toList();
		
	}
	@Override
	public List<Vehicle> searchByModel(String keyword) {
		// TODO Auto-generated method stub
		return vehicles.stream()
						 .filter(vehicle->vehicle.getModel().toLowerCase().contains(keyword.toLowerCase()))
						 .toList();
	
	}

	@Override
	public List<Transaction> getAllTransactons() {
		// TODO Auto-generated method stub
		return transactions;
	}
	
	
	@Override
	public Map<Integer, List<Vehicle>> vehiclesByRentalCount() {
		return rentalCounts.entrySet().stream()
								.collect(Collectors.groupingBy(
										Map.Entry::getValue,
										Collectors.mapping(Map.Entry::getKey, Collectors.toList())));

	}
	
	@Override
	public Map<VehicleType, List<Vehicle>> groupByVehicles() {
		// TODO Auto-generated method stub
		return vehicles.stream()
				.collect(Collectors.groupingBy(Vehicle::getType));

	}
	@Override
	public Map<VehicleType, Integer> rentalsPerVehicle() {
		// TODO Auto-generated method stub
		return rentalCounts.entrySet().stream()
										.collect(
										Collectors.groupingBy(
												entry->entry.getKey().getType(),
												Collectors.summingInt(Map.Entry::getValue))
										);
		
	}
	
	public List<Vehicle> neverRented() {

	    return vehicles.stream()
	            .filter(v -> !rentalCounts.containsKey(v))
	            .toList();
	}
	
	public VehicleType mostPopularVehicleType() {

	    Map<VehicleType, Integer> typeCount = rentalCounts.entrySet().stream()
	            .collect(Collectors.groupingBy(
	                    e -> e.getKey().getType(),
	                    Collectors.summingInt(Map.Entry::getValue)
	            ));
   
	     return typeCount.entrySet().stream()
	       			.max(Map.Entry.comparingByValue())
	       			.map(Map.Entry::getKey)
	       			.orElse(null);
	}
	
	public Map<Vehicle, Integer> mergeRentalMaps(
	        Map<Vehicle, Integer> m1,
	        Map<Vehicle, Integer> m2) {

	    Map<Vehicle, Integer> result = new HashMap<>(m1);

	    m2.forEach((key, value) ->
	            result.merge(key, value, Integer::sum));

	    return result;
	}
	

}
