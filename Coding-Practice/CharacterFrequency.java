package com.practice;

import java.util.Scanner;

public class CharacterFrequency {
	public static void printCharacterFrequency(String sentence) {
		int[] freq=new int[26];
		boolean found=false;
		sentence=sentence.strip().replaceAll("\\s+","").toLowerCase();
		
		for(int i=0;i<sentence.length();i++) {
			char ch=sentence.charAt(i);
			if(ch>='a'&&ch<='z') {
				freq[ch-'a']++;
				found=true;
			}
			
		}
		if(!found) {
			System.out.println("No characters found");
		}
		for(int i=0;i<freq.length;i++) {
			if(freq[i]>0) {
				System.out.println((char)('a'+i)+" "+freq[i]);
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a string:");
        String sentence = scanner.nextLine();
        printCharacterFrequency(sentence);
        scanner.close();
	}
}
