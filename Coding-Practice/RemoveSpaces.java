package com.practice;

import java.util.Scanner;

public class RemoveSpaces {
	public static void printStringWithoutSpaces(String sentence) {
		
		System.out.println(sentence.replaceAll("\\s+", ""));
	}
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a string:");
        String sentence = scanner.nextLine();
        printStringWithoutSpaces(sentence);
        scanner.close();
        
	}
}
