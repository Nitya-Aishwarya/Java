package com.hospital.management.models;

import com.hospital.management.enums.Gender;
import com.hospital.management.enums.PatientStatus;

// Sealed Patient class
public abstract sealed class Patient permits InPatient, OutPatient {
    private final int id;
    private String name;
    private Gender gender;
    private int age;
    private PatientStatus status;

    protected Patient(int id, String name, Gender gender, int age, PatientStatus status) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.status = status;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public Gender getGender() { return gender; }
    public int getAge() { return age; }
    public PatientStatus getStatus() { return status; }
    public void setStatus(PatientStatus status) { this.status = status; }
}




