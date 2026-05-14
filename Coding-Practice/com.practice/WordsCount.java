package com.practice;

import java.util.Scanner;

public class WordsCount {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String sentence=readSentence(sc);
		printWordsCount(sentence);
		sc.close();
	}
	
	public static String readSentence(Scanner scanner) {
		System.out.println("Enter a sentence");
		String sentence=scanner.nextLine();
		return sentence;
	}
	public static void printWordsCount(String sentence) {
		String[] words=sentence.split("\\s+");
		System.out.println("Words count in sentence: "+words.length);
	}
}
