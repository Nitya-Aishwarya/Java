package com.practice;


class Outer{
	class Inner{
		void print() {
			System.out.println("Outer-Inner class");
		}
	}
}
public class InnerClass {
	public static void main(String[] args) {
		Outer.Inner obj=new Outer().new Inner();
		obj.print();
	}
}
