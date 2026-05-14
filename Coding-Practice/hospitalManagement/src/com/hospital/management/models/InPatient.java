package com.hospital.management.models;

import com.hospital.management.enums.Gender;
import com.hospital.management.enums.PatientStatus;

public final class InPatient extends Patient {
    private int roomNumber;

    public InPatient(int id, String name, Gender gender, int age, PatientStatus status, int roomNumber) {
        super(id, name, gender, age, status);
        this.roomNumber = roomNumber;
    }

    public int getRoomNumber() { return roomNumber; }
}
