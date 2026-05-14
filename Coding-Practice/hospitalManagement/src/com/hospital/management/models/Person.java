package com.hospital.management.models;

import com.hospital.management.enums.Gender;

public abstract class Person {
    protected int id;
    protected String name;
    protected Gender gender;

    public Person(int id, String name, Gender gender) {
        this.id = id;
        this.name = name;
        this.gender = gender;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public Gender getGender() { return gender; }
}
