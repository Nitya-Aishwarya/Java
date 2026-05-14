package com.practice.programs;

import java.util.Scanner;

public class SwappingWithoutThirdVariable {
	public static void main(String[] args) {
		int num1,num2;
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter Numbers:");
		num1=sc.nextInt(); //24
		num2=sc.nextInt(); //30
//		num1=num1+num2;
//		num2=num1-num2;
//		num1=num1-num2;
		num1=num1^num2;  //24^30
		num2=num1^num2;  //24^30^30 => 24^0 =>24
		num1=num1^num2;	 //24^30^24 => 30^0 =>30
		
		System.out.println("Num1: "+num1+"\nNum2: "+num2);
		sc.close();
	}
}
