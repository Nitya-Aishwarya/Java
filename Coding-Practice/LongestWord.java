package com.practice;

import java.util.Scanner;

public class LongestWord {

    public static String findLongestWord(String sentence) {

        sentence = sentence.strip();

        String[] words = sentence.split("\\s+");

        String longest = "";

        for (String word : words) {

            if (word.length() > longest.length()) {
                longest = word;
            }
        }

        return longest;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a sentence:");
        String sentence = scanner.nextLine();

        String longestWord = findLongestWord(sentence);

        System.out.println("Longest word: " + longestWord);

        scanner.close();
    }
}