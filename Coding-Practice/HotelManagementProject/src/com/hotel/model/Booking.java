package com.hotel.model;

import com.hotel.model.enums.BookingStatus;
import java.time.LocalDate;

public class Booking {
    private int bookingId;
    private int roomId;
    private int userId;
    private LocalDate startDate;
    private LocalDate endDate;
    private BookingStatus status;

    public Booking(int bookingId, int roomId, int userId, LocalDate startDate, LocalDate endDate) {
        this.bookingId = bookingId;
        this.roomId = roomId;
        this.userId = userId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = BookingStatus.CONFIRMED;
    }

    public int getBookingId() { return bookingId; }
    public int getRoomId() { return roomId; }
    public int getUserId() { return userId; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public BookingStatus getStatus() { return status; }
    public void setStatus(BookingStatus status) { this.status = status; }

    @Override
    public String toString() {
        return "Booking ID: " + bookingId +
               ", Room ID: " + roomId +
               ", Start: " + startDate +
               ", End: " + endDate +
               ", Status: " + status;
    }
}
