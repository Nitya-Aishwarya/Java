package com.practice;

import java.util.Scanner;

public class InputOutput {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter you name:");
		String name=sc.nextLine();
		System.out.println("Enter your age:");
		Integer age=Integer.parseInt(sc.nextLine());
//		int age=sc.nextInt();
		
		System.out.println("Name of the college:");
		String college=sc.nextLine();
		System.out.println("Year of birth:");
		int year=sc.nextInt();
		
		System.out.println("Name: "+name+" "+"\nAge: "+age+"\nCollege: "+college+"\nYear of birth: "+year);
		sc.close();
	}
}
