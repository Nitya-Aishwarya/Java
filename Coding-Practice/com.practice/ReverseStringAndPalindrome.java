package com.practice;

import java.util.Scanner;

public class ReverseStringAndPalindrome {
	public static void main(String[] args) {
		Scanner scanner=new Scanner(System.in);
		String string=readInput(scanner);
		reverseString(string);
		if(isPalindrome(string))System.out.println("Palindrome");
		else System.out.println("Not a palindrome");
	}

	public static String readInput(Scanner scanner) {
		System.out.println("Enter a String:");
		return scanner.nextLine();
	}
	
	public static void reverseString(String string) {
//		StringBuilder originalString=new StringBuilder(string);
//		System.out.println("Reversed string :"+originalString.reverse());
//		for(int i=string.length()-1;i>=0;i--) {
//			System.out.print(string.charAt(i));
//		}
		StringBuilder sb=new StringBuilder();
		for(int i=string.length()-1;i>=0;i--) {
			sb.append(string.charAt(i));
		}
		System.out.println(sb.toString());
	}
	
	public static boolean isPalindrome(String s) {

	    s = s.replaceAll("\\s+", "").toLowerCase();

	    int left = 0;
	    int right = s.length() - 1;

	    while (left < right) {

	        if (s.charAt(left) != s.charAt(right)) {
	            return false;
	        }

	        left++;
	        right--;
	    }

	    return true;
	}
}
