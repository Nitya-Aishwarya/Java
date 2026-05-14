package com.hotel.app;

import com.hotel.model.enums.*;
import com.hotel.model.*;
import com.hotel.service.HotelManagementSystem;
import com.hotel.exception.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.List;

public class HotelBookingApp {
    private Scanner sc = new Scanner(System.in);
    private HotelManagementSystem hms = new HotelManagementSystem();
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void main(String[] args) {
        new HotelBookingApp().run();
    }

    public void run() {
        try {
            hms.addRoom(101, RoomType.SINGLE, 50);
            hms.addRoom(102, RoomType.DOUBLE, 80);
            hms.addRoom(103, RoomType.SUITE, 150);
        } catch (BookingException e) {
            System.out.println(e.getMessage());
        }

        boolean exit = false;
        while (!exit) {
            System.out.println("\n--- Hotel Booking System ---");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choice: ");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> register();
                case 2 -> login();
                case 3 -> exit = true;
                default -> System.out.println("Invalid choice!");
            }
        }
        System.out.println("Thank you for using Hotel Booking System!");
    }

    private void register() {
        try {
            System.out.print("Name: "); 
            String name = sc.nextLine();
            System.out.print("Email: ");
            String email = sc.nextLine();
            System.out.print("Password: ");
            String password = sc.nextLine();
            System.out.print("User Type (ADMIN/CUSTOMER): "); 
            UserRole type = UserRole.valueOf(sc.nextLine().toUpperCase());
            hms.registerUser(name, email, password, type);
            System.out.println("Registration successful!");
        } catch (BookingException | IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void login() {
        try {
            System.out.print("Email: "); 
            String email = sc.nextLine();
            System.out.print("Password: ");
            String password = sc.nextLine();
            User user = hms.loginUser(email, password);
            System.out.println("Login successful! Welcome " + user.getName());
            if (user.getRole() == UserRole.ADMIN) 
            	adminMenu(user);
            else userMenu(user);
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void userMenu(User user) {
        boolean logout = false;
        while (!logout) {
            System.out.println("\n--- User Menu ---");
            System.out.println("1. View Rooms");
            System.out.println("2. Book Room");
            System.out.println("3. Cancel Booking");
            System.out.println("4. View My Bookings");
            System.out.println("5. Logout");
            System.out.print("Choice: "); 
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> viewRooms();
                case 2 -> bookRoom(user);
                case 3 -> cancelBooking(user);
                case 4 -> viewUserBookings(user);
                case 5 -> logout = true;
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private void adminMenu(User user) {
        boolean logout = false;
        while (!logout) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. View Rooms");
            System.out.println("2. Add Room");
            System.out.println("3. View All Bookings");
            System.out.println("4. Logout");
            System.out.print("Choice: "); 
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> viewRooms();
                case 2 -> addRoom();
                case 3 -> viewAllBookings();
                case 4 -> logout = true;
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private void viewRooms() {
        List<Room> rooms = hms.viewRooms();
        if (rooms.isEmpty()) 
        	System.out.println("No rooms available.");
        else rooms.forEach(System.out::println);
    }

    private void addRoom() {
        try {
            System.out.print("Room ID: "); 
            int id = Integer.parseInt(sc.nextLine());
            System.out.print("Room Type (SINGLE/DOUBLE/SUITE): "); 
            RoomType type = RoomType.valueOf(sc.nextLine().toUpperCase());
            System.out.print("Price per Night: "); 
            double price = Double.parseDouble(sc.nextLine());
            hms.addRoom(id, type, price);
            System.out.println("Room added successfully!");
        } catch (BookingException | IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void bookRoom(User user) {
        try {
            System.out.print("Enter Room ID: "); 
            int roomId = Integer.parseInt(sc.nextLine());
            System.out.print("Enter Start Date (yyyy-MM-dd): "); 
            LocalDate start = LocalDate.parse(sc.nextLine(), dtf);
            System.out.print("Enter End Date (yyyy-MM-dd): "); 
            LocalDate end = LocalDate.parse(sc.nextLine(), dtf);
            hms.bookRoom(roomId, user.getUserId(), start, end);
            System.out.println("Room booked successfully!");
        } catch (BookingException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void cancelBooking(User user) {
        try {
            System.out.print("Enter Booking ID to cancel: "); 
            int bookingId = Integer.parseInt(sc.nextLine());
            hms.cancelBooking(bookingId, user.getUserId());
            System.out.println("Booking cancelled successfully!");
        } catch (BookingException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void viewUserBookings(User user) {
        List<Booking> bookings = hms.viewUserBookings(user.getUserId());
        if (bookings.isEmpty()) 
        	System.out.println("No bookings found.");
        else bookings.forEach(System.out::println);
    }

    private void viewAllBookings() {
        List<Booking> bookings = hms.viewAllBookings();
        if (bookings.isEmpty()) 
        	System.out.println("No bookings found.");
        else bookings.forEach(System.out::println);
    }
}
