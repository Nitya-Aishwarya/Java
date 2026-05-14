package com.vehicle.models;

import java.util.Objects;

import com.vehicle.exceptions.InvalidException;
import com.vehicle.exceptions.InvalidVehicleException;
import com.vehicle.interfaces.TimeTracker;
import com.vehicle.models.enums.TransactionType;

// Transaction POJO class
public class Transaction implements TimeTracker {

    private static int counter = 1;

    private final int transactionId;
    private final String vehicleId;
    private final int customerId;
    private final TransactionType type;
    private final String timestamp;

    public Transaction(String vehicleId, int customerId, TransactionType type) {
        if (vehicleId == null || vehicleId.isBlank()) {
            throw new InvalidVehicleException("Invalid vehicleId");
        }
        if (type == null) {
            throw new InvalidException("Transaction type cannot be null");
        }

        this.transactionId = counter++;
        this.vehicleId = vehicleId;
        this.customerId = customerId;
        this.type = type;
        this.timestamp = now(); 
    }

    public int getTransactionId() {
        return transactionId;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public TransactionType getType() {
        return type;
    }

    public String getTimestamp() {
        return timestamp;
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Transaction)) return false;
        Transaction other = (Transaction) obj;
        return this.transactionId == other.transactionId;
    }

    @Override
    public String toString() {
        return String.join("|",
                String.valueOf(transactionId),
                String.valueOf(customerId),
                vehicleId,
                type.toString(),
                timestamp
        );
    }
}