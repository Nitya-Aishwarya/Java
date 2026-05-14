package com.practice.programs;

import java.util.Scanner;

public class Palindrome {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter to check palindrome or not: ");
		String string=sc.nextLine();
		boolean check =true;
		string=string.toLowerCase();
		for(int i=0;i<string.length()/2;i++) {
			if(string.charAt(i)!=string.charAt(string.length()-i-1)) {
				
			check=false;
			break;
			}
		}
		if(check) {
			System.out.println("Its a palindrome");
		}else
		System.out.println("Not a palindrome");
		sc.close();
	}
}
