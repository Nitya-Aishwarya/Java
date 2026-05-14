package com.hospital.management.models;

import com.hospital.management.enums.Department;
import com.hospital.management.enums.Gender;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public final class Doctor extends Person {
    private Department department;
    private List<LocalDateTime> availableSlots = new ArrayList<>();

    public Doctor(int id, String name, Gender gender, Department department) {
        super(id, name, gender);
        this.department = department;
    }

    public Department getDepartment() { return department; }
    public List<LocalDateTime> getAvailableSlots() { return availableSlots; }
    public void addAvailableSlot(LocalDateTime slot) { availableSlots.add(slot); }
    public boolean isAvailable(LocalDateTime slot) { return availableSlots.contains(slot); }
    public void removeSlot(LocalDateTime slot) { availableSlots.remove(slot); }
}
