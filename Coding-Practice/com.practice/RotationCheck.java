package com.practice;

import java.util.Scanner;

public class RotationCheck {
	public static void checkRotation(String word1,String word2) {
		word1=word1.strip();
		word2=word2.strip();
		if(word1.length()!=word2.length()) {
			System.out.println("Not a rotation");
			return;
		}
		
		String combined=word1+word1;
		if(combined.contains(word2)) {
			System.out.println("Is a rotation");
		}else {
			System.out.println("Not a rotation");
		}
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

        System.out.println("Enter word 1:");
        String word1 = scanner.nextLine();

        System.out.println("Enter word 2:");
        String word2 = scanner.nextLine();
        
        checkRotation(word1, word2);
        scanner.close();
	}
}
