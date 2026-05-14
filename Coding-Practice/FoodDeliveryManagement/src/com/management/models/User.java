package com.management.models;

import java.util.Objects;
import java.util.UUID;

import com.management.models.enums.UserRole;

public abstract class User {

    private final String userId;
    private String name;
    private String phoneNumber;

    public User(String name, String phoneNumber) {
        this.userId = UUID.randomUUID().toString();

        this.name = Objects.requireNonNull(name, "Name is required");
        this.phoneNumber = Objects.requireNonNull(phoneNumber, "Phone number required");
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = Objects.requireNonNull(name, "Name is required");
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = Objects.requireNonNull(phoneNumber, "Phone number required");
    }

    public abstract UserRole getUserRole();

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof User)) return false;

        User other = (User) obj;
        return Objects.equals(userId, other.userId);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}