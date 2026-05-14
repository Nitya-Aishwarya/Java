package com.practice;

class Person {
    String name;
}

public class TestPassByValue {

    static void change(Person p) {
        p = new Person();
        p.name = "Nitya";
    }

    public static void main(String[] args) {

        Person person = new Person();
        person.name = "Aishwarya";

        change(person);

        System.out.println(person.name);
    }
}
