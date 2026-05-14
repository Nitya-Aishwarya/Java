package com.practice.programs;

import java.util.Scanner;

public class EvenOdd {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter a number: ");
		int number=sc.nextInt();
		
		if((number&1)==1) {
			System.out.println("Odd number");
		}else {
			System.out.println("Even number");
		}
		
		sc.close();
	}
}
