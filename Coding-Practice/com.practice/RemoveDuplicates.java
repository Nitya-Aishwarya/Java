package com.practice;

import java.util.Scanner;

public class RemoveDuplicates {
	public static void removeDuplicates(String word) {
		boolean[] seen=new boolean[256];
		
		word=word.strip().replaceAll("\\s+", "");
		
		StringBuilder wordWithoutDuplicates=new StringBuilder();
		
		for(char ch:word.toCharArray()) {
			if(!seen[ch]) {
				seen[ch]=true;
				wordWithoutDuplicates.append(ch);
			}
		}
		
		System.out.println(wordWithoutDuplicates.toString());
	}
	
	public static void main(String[] args) {
		 Scanner scanner = new Scanner(System.in);

	     System.out.println("Enter a string:");

	     String word = scanner.nextLine();
	     removeDuplicates(word);
	     scanner.close();
	}
}
