package com.hotel.model;

import com.hotel.model.enums.RoomType;

public class Room {
    private int roomId;
    private RoomType type;
    private double pricePerNight;

    public Room(int roomId, RoomType type, double pricePerNight) {
        this.roomId = roomId;
        this.type = type;
        this.pricePerNight = pricePerNight;
    }

    public int getRoomId() { return roomId; }
    public RoomType getType() { return type; }
    public double getPricePerNight() { return pricePerNight; }

    @Override
    public String toString() {
        return "Room ID: " + roomId + ", Type: " + type + ", Price/Night: $" + pricePerNight;
    }
}
