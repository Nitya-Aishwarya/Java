package com.hospital.management;

import com.hospital.management.models.*;
import com.hospital.management.enums.*;
import com.hospital.management.exceptions.*;
import com.hospital.management.notifications.Notification;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class HospitalManagementSystem {
    private final Map<Integer, Patient> patients = new HashMap<>();
    private final Map<Integer, Doctor> doctors = new HashMap<>();
    private final Map<Integer, Appointment> appointments = new HashMap<>();
    private final AtomicInteger nextAppointmentId = new AtomicInteger(1);
    private static final String APPOINTMENT_FILE = "appointments.txt";

    // Default notification
    private Notification notification = message -> System.out.println("[NOTIFICATION] " + message);

    public void setNotification(Notification notification) { this.notification = notification; }

    public void addPatient(Patient p) { patients.put(p.getId(), p); }
    public void addDoctor(Doctor d) { doctors.put(d.getId(), d); }

    public Optional<Patient> findPatientById(int id) { return Optional.ofNullable(patients.get(id)); }
    public Optional<Doctor> findDoctorById(int id) { return Optional.ofNullable(doctors.get(id)); }

    public Appointment scheduleAppointment(int patientId, int doctorId, LocalDateTime time) throws PatientNotFoundException {
        Patient patient = findPatientById(patientId)
                .orElseThrow(() -> new PatientNotFoundException("Patient ID " + patientId + " not found!"));
        Doctor doctor = findDoctorById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor ID " + doctorId + " not found!"));

        if (!doctor.isAvailable(time)) {
            System.out.println("Doctor " + doctor.getName() + " is not available at " + time);
            return null;
        }

        int appointmentId = nextAppointmentId.getAndIncrement();
        Appointment appointment = new Appointment(appointmentId, patientId, doctorId, time, AppointmentStatus.SCHEDULED);
        appointments.put(appointmentId, appointment);
        saveAppointmentToFile(appointment);
        doctor.removeSlot(time);

        // Send notification
        notification.send("Appointment confirmed: Patient " + patient.getName() + ", Doctor " + doctor.getName() + ", Time: " + time);

        return appointment;
    }

    private void saveAppointmentToFile(Appointment appointment) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(APPOINTMENT_FILE, true))) {
            writer.write(appointment.toString());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving appointment: " + e.getMessage());
        }
    }

    public void showAppointmentsForPatient(int patientId) {
        appointments.values().stream()
                .filter(a -> a.patientId() == patientId)
                .sorted(Comparator.comparing(Appointment::appointmentTime))
                .forEach(System.out::println);
    }

    public void showAvailableDoctors() {
        doctors.values().stream().forEach(doctor -> {
            System.out.println("Doctor ID: " + doctor.getId() + ", Name: " + doctor.getName() + ", Dept: " + doctor.getDepartment());
            if (doctor.getAvailableSlots().isEmpty()) {
                System.out.println("   No available slots");
            } else {
                doctor.getAvailableSlots().forEach(slot -> System.out.println("   Available: " + slot));
            }
        });
    }

    public void placeAppointment(int patientId, int doctorId, LocalDateTime slot) {
        try {
            Appointment appointment = scheduleAppointment(patientId, doctorId, slot);
            if (appointment != null) System.out.println("Appointment placed successfully for patient ID " + patientId);
        } catch (PatientNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static class Utils {
        private static final AtomicInteger doctorIdCounter = new AtomicInteger(1);
        public static int generateDoctorId() { return doctorIdCounter.getAndIncrement(); }
    }
}
