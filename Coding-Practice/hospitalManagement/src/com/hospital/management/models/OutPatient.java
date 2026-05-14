package com.hospital.management.models;

import com.hospital.management.enums.Gender;
import com.hospital.management.enums.PatientStatus;
import java.time.LocalDateTime;

public final class OutPatient extends Patient {
    private LocalDateTime nextVisit;

    public OutPatient(int id, String name, Gender gender, int age, PatientStatus status, LocalDateTime nextVisit) {
        super(id, name, gender, age, status);
        this.nextVisit = nextVisit;
    }

    public LocalDateTime getNextVisit() { return nextVisit; }
}
