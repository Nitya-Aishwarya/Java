package com.practice.programs;

import java.util.Scanner;

public class ReverseNumber {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter a number: ");
		int number=sc.nextInt();
		int temp=0;
		while(number>0) {
			int lastDigit=number%10;
			temp=temp*10+lastDigit;
			number/=10;
		}
		
		System.out.println("Reversed Number:"+temp);
		sc.close();
	}
}
