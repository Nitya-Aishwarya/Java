package com.hotel.model;

import com.hotel.model.enums.UserRole;

public class User {
    private int userId;
    private String name;
    private String email;
    private String password;
    private UserRole role;

    public User(int userId, String name, String email, String password, UserRole role) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public int getUserId() { return userId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public UserRole getRole() { return role; }

    @Override
    public String toString() {
        return name + " (" + role + ")";
    }
}