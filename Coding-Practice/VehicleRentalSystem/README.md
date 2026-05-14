# Vehicle Rental Management System

The Vehicle Rental Management System is a Java-based application designed to manage vehicle rentals efficiently. This project demonstrates the use of Object-Oriented Programming (OOP), Java Collections, Exception Handling, and the Java Stream API to build a simple yet functional rental management system.

The application allows users to add vehicles and customers, rent and return vehicles, track rental transactions, and generate useful rental analytics.

---

# Features

* Add new vehicles to the system
* Register customers
* Rent vehicles to customers
* Return rented vehicles
* View all available vehicles
* Search vehicles by model name
* Track all rental and return transactions
* Display top rented vehicles
* Group vehicles based on vehicle type
* Calculate total rentals for each vehicle type
* Identify vehicles that were never rented
* Find the most popular vehicle category
* Merge rental statistics from multiple maps

---

# Technologies Used

* Java
* Java Collections Framework
* Java Stream API
* Object-Oriented Programming (OOP)

---

# Project Structure

```text id="wgvjlwm"
src/
│
├── com.vehicle.models
│   ├── Vehicle.java
│   ├── Customer.java
│   ├── Transaction.java
│   └── enums
│       ├── VehicleType.java
│       └── TransactionType.java
│
├── com.vehicle.exceptions
│   ├── AlreadyExistsException.java
│   ├── CustomerNotFoundException.java
│   ├── InvalidVehicleException.java
│   ├── VehicleNotAvailableException.java
│   └── VehicleNotFoundException.java
│
├── com.vehicle.interfaces
│   └── TimeTracker.java
│
├── com.vehicle.service
│   ├── RentalService.java
│   └── RentalServiceImplementation.java
```

---

# Functionalities

## 1. Add Vehicle

The system allows adding new vehicles.
If a vehicle already exists, an exception is thrown.

```java id="8ylbgw"
addVehicle(Vehicle vehicle)
```

---

## 2. Add Customer

Customers can be registered in the system.
Duplicate customers are not allowed.

```java id="ptmxyc"
addCustomer(Customer customer)
```

---

## 3. Rent Vehicle

A customer can rent an available vehicle.
The system checks:

* Whether the customer exists
* Whether the vehicle exists
* Whether the vehicle is currently available

If all validations pass, the rental transaction is recorded.

```java id="l9d8x6"
rentVehicle(int customerId, String vehicleId)
```

---

## 4. Return Vehicle

Customers can return rented vehicles.
The system validates whether the vehicle was actually rented before processing the return.

```java id="v9gzx5"
returnVehicle(int customerId, String vehicleId)
```

---

## 5. View Available Vehicles

Displays all vehicles that are currently available for rent.

```java id="84s4ix"
availableVehicles()
```

---

## 6. Search Vehicles by Model

Users can search vehicles using model names.

```java id="8drm0q"
searchByModel(String keyword)
```

---

## 7. View Top Rental Vehicles

Returns the top rented vehicles based on rental count.

```java id="vpm3to"
top3RentalVehicles()
```

---

## 8. Group Vehicles by Type

Groups all vehicles according to their category such as Car, Bike, or Truck.

```java id="0nbcv9"
groupByVehicles()
```

---

## 9. Rentals Per Vehicle Type

Calculates the total number of rentals for each vehicle type.

```java id="yefhse"
rentalsPerVehicle()
```

---

## 10. Never Rented Vehicles

Returns the list of vehicles that have never been rented.

```java id="v7g05t"
neverRented()
```

---

## 11. Most Popular Vehicle Type

Finds the vehicle type with the highest number of rentals.

```java id="v1d4bk"
mostPopularVehicleType()
```

---

# Exception Handling

The project uses custom exceptions to improve error handling and application reliability.

### Custom Exceptions Used

* `AlreadyExistsException`
* `CustomerNotFoundException`
* `VehicleNotFoundException`
* `VehicleNotAvailableException`
* `InvalidVehicleException`

---

# Concepts Demonstrated

This project demonstrates the following Java concepts:

* Object-Oriented Programming
* Interfaces and Abstraction
* Exception Handling
* Java Collections
* Java Stream API
* Functional Programming
* Grouping and Mapping Operations

---

# Example Workflow

```text id="ywljix"
1. Add vehicles to the system
2. Register customers
3. Rent vehicles
4. Return rented vehicles
5. View rental statistics and reports
```


# Conclusion

This project is a good example of a real-world Java application that combines OOP principles with Java Collections and Stream API operations. It can be used as a learning project for understanding rental management systems and improving Java programming skills.

