package com.practice;


abstract class Animal {

    abstract void sound();

    void sleep() {
        System.out.println("Animal sleeps");
    }
}

class Dog extends Animal {

    void sound() {
        System.out.println("Dog barks");
    }
}
public class AbstractClass {
	public static void main(String[] args) {
		Animal dog=new Dog();
		dog.sound();//abstract class
		Animal animal=new Animal() {//anonymous inner class
			
			@Override
			void sound() {
				// TODO Auto-generated method stub
				System.out.println("Animal Sound");
			}
		};
		animal.sound();
	}
}
