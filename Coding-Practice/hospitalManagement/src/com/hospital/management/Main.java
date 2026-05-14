package com.hospital.management;

import java.time.LocalDateTime;
import java.util.Scanner;

import com.hospital.management.enums.Department;
import com.hospital.management.enums.Gender;
import com.hospital.management.enums.PatientStatus;
import com.hospital.management.models.Doctor;
import com.hospital.management.models.InPatient;
import com.hospital.management.models.OutPatient;
import com.hospital.management.models.Patient;

public class Main {
    public static void main(String[] args) {
        HospitalManagementSystem hms = new HospitalManagementSystem();

        // Set custom notification (Lambda)
        hms.setNotification(msg -> System.out.println("[SMS NOTIFICATION] " + msg));

        // Add Doctors
        Doctor dr1 = new Doctor(HospitalManagementSystem.Utils.generateDoctorId(), "Alice", Gender.FEMALE, Department.CARDIOLOGY);
        Doctor dr2 = new Doctor(HospitalManagementSystem.Utils.generateDoctorId(), "Bob", Gender.MALE, Department.NEUROLOGY);
        dr1.addAvailableSlot(LocalDateTime.now().plusHours(1));
        dr1.addAvailableSlot(LocalDateTime.now().plusHours(2));
        dr2.addAvailableSlot(LocalDateTime.now().plusHours(1));

        hms.addDoctor(dr1);
        hms.addDoctor(dr2);

        // Add Patients
        Patient p1 = new OutPatient(1, "John", Gender.MALE, 30, PatientStatus.UNDER_OBSERVATION, LocalDateTime.now().plusDays(7));
        Patient p2 = new InPatient(2, "Mary", Gender.FEMALE, 25, PatientStatus.ADMITTED, 101);

        hms.addPatient(p1);
        hms.addPatient(p2);

        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n--- Hospital Management ---");
            System.out.println("1. Show Available Doctors");
            System.out.println("2. Place Appointment");
            System.out.println("3. Show Patient Appointments");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> hms.showAvailableDoctors();
                case 2 -> {
                    System.out.print("Enter Patient ID: ");
                    int patientId = sc.nextInt();
                    System.out.print("Enter Doctor ID: ");
                    int doctorId = sc.nextInt();
                    System.out.print("Enter appointment hour from now: ");
                    int hour = sc.nextInt();
                    LocalDateTime slot = LocalDateTime.now().plusHours(hour);
                    hms.placeAppointment(patientId, doctorId, slot);
                }
                case 3 -> {
                    System.out.print("Enter Patient ID: ");
                    int patientId = sc.nextInt();
                    hms.showAppointmentsForPatient(patientId);
                }
                case 4 -> {
                    System.out.println("Exiting...");
                    running = false;
                }
                default -> System.out.println("Invalid choice!");
            }
        }

        sc.close();
    }
}

