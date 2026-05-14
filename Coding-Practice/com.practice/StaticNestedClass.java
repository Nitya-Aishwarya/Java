package com.practice;


class NestedClass{
	static class InnerClass{
		void print() {
			System.out.println("Static Inner class");
		}
	}
}
public class StaticNestedClass {
	public static void main(String[] args) {
		NestedClass.InnerClass innerObj=new NestedClass.InnerClass();
		innerObj.print();
	}
}
