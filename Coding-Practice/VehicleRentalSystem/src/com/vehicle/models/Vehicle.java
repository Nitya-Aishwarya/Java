package com.vehicle.models;

import java.util.Objects;

import com.vehicle.exceptions.InvalidException;
import com.vehicle.exceptions.InvalidVehicleException;
import com.vehicle.models.enums.VehicleType;

// Vehicle POJO class
public class Vehicle {

    private final String vehicleId;
    private String model;
    private VehicleType type;
    private double rentalRatePerDay;
    private boolean available;

    public Vehicle(String vehicleId, String model, VehicleType type,
                   double rentalRatePerDay, boolean available) {

        if (vehicleId == null || vehicleId.strip().isEmpty()) {
            throw new InvalidVehicleException("Invalid vehicleId");
        }

        this.vehicleId = vehicleId.strip();
        setModel(model);
        setType(type);
        setRentalRatePerDay(rentalRatePerDay);
        this.available = available;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        if (model == null || model.strip().isEmpty()) {
            throw new InvalidException("Invalid model");
        }
        this.model = model.strip();
    }

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        if (type == null) {
            throw new InvalidException("Invalid vehicle type");
        }
        this.type = type;
    }

    public double getRentalRatePerDay() {
        return rentalRatePerDay;
    }

    public void setRentalRatePerDay(double rentalRatePerDay) {
        if (rentalRatePerDay < 0) {
            throw new InvalidException("Invalid rental rate");
        }
        this.rentalRatePerDay = rentalRatePerDay;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public int hashCode() {
        return Objects.hash(vehicleId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Vehicle)) return false;
        Vehicle other = (Vehicle) obj;
        return Objects.equals(this.vehicleId, other.vehicleId);
    }

    @Override
    public String toString() {
        return String.join(" | ",
                vehicleId,
                type.toString(),
                model,
                rentalRatePerDay + "/day",
                available ? "Available" : "Not Available"
        );
    }
}