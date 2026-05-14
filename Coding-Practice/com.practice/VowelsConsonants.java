package com.practice;

import java.util.Scanner;


public class VowelsConsonants {
	
	public void printCountVowelConsonants(String word) {
		int vowels=0,consonants=0;
		char[] wordsArray=word.toLowerCase().toCharArray();
		
		for(int i=0;i<wordsArray.length;i++) {
			char character=wordsArray[i];
			
			if(character>='a' && character<='z') {
				
				if("aeiou".indexOf(character)!=-1)vowels++;
				else consonants++;
			}
		}
		
		System.out.println("Vowels: "+vowels+"\nConsonants: "+consonants);
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a string:");
        String word = scanner.nextLine();
        VowelsConsonants vowelsConsonants=new VowelsConsonants();
        vowelsConsonants.printCountVowelConsonants(word);
        scanner.close();
	}
}
