package com.hospital.management.models;

import com.hospital.management.enums.AppointmentStatus;

import java.time.LocalDateTime;

public record Appointment(int appointmentId, int patientId, int doctorId, LocalDateTime appointmentTime, AppointmentStatus status) {
    @Override
    public String toString() {
        return appointmentId + "," + patientId + "," + doctorId + "," + appointmentTime + "," + status;
    }
}
