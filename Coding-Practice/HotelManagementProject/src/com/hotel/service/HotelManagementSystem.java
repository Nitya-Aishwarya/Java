package com.hotel.service;

import com.hotel.model.*;
import com.hotel.model.enums.*;
import com.hotel.exception.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HotelManagementSystem {
    private List<User> users = new ArrayList<>();
    private List<Room> rooms = new ArrayList<>();
    private List<Booking> bookings = new ArrayList<>();
    private int userCounter = 1;
    private int bookingCounter = 1;

    public void registerUser(String name, String email, String password, UserRole userType) throws BookingException {
        for (User u : users) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                throw new BookingException("Email already exists!");
            }
        }
        users.add(new User(userCounter++, name, email, password, userType));
    }

    public User loginUser(String email, String password) throws UserNotFoundException {
        for (User u : users) {
            if (u.getEmail().equalsIgnoreCase(email) && u.getPassword().equals(password)) {
                return u;
            }
        }
        throw new UserNotFoundException("Invalid email or password.");
    }

    public void addRoom(int roomId, RoomType type, double pricePerNight) throws BookingException {
        for (Room r : rooms) {
            if (r.getRoomId() == roomId) throw new BookingException("Room ID already exists!");
        }
        rooms.add(new Room(roomId, type, pricePerNight));
    }

    public List<Room> viewRooms() { return rooms; }

    private Room getRoomById(int roomId) { 
        for (Room r : rooms) if (r.getRoomId() == roomId) return r;
        return null;
    }

    public boolean isRoomAvailable(int roomId, LocalDate start, LocalDate end) {
        for (Booking b : bookings) {
            if (b.getRoomId() == roomId && b.getStatus() == BookingStatus.CONFIRMED) {
                if (!(b.getEndDate().isBefore(start) || b.getStartDate().isAfter(end))) return false;
            }
        }
        return true;
    }

    public void bookRoom(int roomId, int userId, LocalDate start, LocalDate end) 
            throws RoomNotAvailableException, DateInvalidException {
        if (start.isAfter(end) || start.isBefore(LocalDate.now())) {
            throw new DateInvalidException("Invalid date range.");
        }
        Room room = getRoomById(roomId);
        if (room == null) throw new RoomNotAvailableException("Room does not exist.");
        if (!isRoomAvailable(roomId, start, end)) throw new RoomNotAvailableException("Room not available for these dates.");
        bookings.add(new Booking(bookingCounter++, roomId, userId, start, end));
    }

    public void cancelBooking(int bookingId, int userId) throws BookingException {
        Booking toCancel = null;
        for (Booking b : bookings) {
            if (b.getBookingId() == bookingId && b.getUserId() == userId) {
                toCancel = b;
                break;
            }
        }
        if (toCancel != null) {
            toCancel.setStatus(BookingStatus.CANCELLED);
        } else throw new BookingException("Booking not found or you do not have permission to cancel.");
    }

    public List<Booking> viewUserBookings(int userId) {
        List<Booking> userBookings = new ArrayList<>();
        for (Booking b : bookings) if (b.getUserId() == userId) userBookings.add(b);
        return userBookings;
    }

    public List<Booking> viewAllBookings() { return bookings; }
}
