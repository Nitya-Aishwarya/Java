package com.vehicle.models;

import java.util.Objects;

import com.vehicle.exceptions.InvalidCustomerException;

// Customer POJO class
public class Customer {

    private static int counter = 1;

    private final int customerId;
    private String name;
    private String phone;

    public Customer(String name, String phone) {
        setName(name);
        setPhone(phone);
        this.customerId = counter++;
    }

    @Override
    public String toString() {
        return String.join(" | ",
                String.valueOf(customerId),
                name,
                phone
        );
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.strip().isEmpty()) {
            throw new InvalidCustomerException("Invalid name");
        }
        this.name = name.strip();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (phone == null || !phone.matches("\\d{10}")) {
            throw new InvalidCustomerException("Invalid phone number");
        }
        this.phone = phone;
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Customer)) return false;
        Customer other = (Customer) obj;
        return this.customerId == other.customerId;
    }
}