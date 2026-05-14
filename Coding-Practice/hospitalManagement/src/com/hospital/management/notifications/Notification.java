package com.hospital.management.notifications;

@FunctionalInterface
public interface Notification {
    void send(String message);
}
