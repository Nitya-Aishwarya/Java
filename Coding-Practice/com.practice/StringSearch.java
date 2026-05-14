package com.practice;

import java.util.Scanner;

public class StringSearch {

    public static int findFirstOccurrence(String text, String pattern) {

        int textLength = text.length();
        int patternLength = pattern.length();

        // Edge cases
        if (patternLength == 0) return 0;
        if (patternLength > textLength) return -1;

        // Slide over the text
        for (int i = 0; i <= textLength - patternLength; i++) {

            int j = 0;

            // match pattern with substring
            while (j < patternLength &&
                   text.charAt(i + j) == pattern.charAt(j)) {
                j++;
            }

            // full match found
            if (j == patternLength) {
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter main string:");
        String text = scanner.nextLine();

        System.out.println("Enter string to search:");
        String pattern = scanner.nextLine();

        int index = findFirstOccurrence(text, pattern);

        System.out.println("First occurrence index: " + index);

        scanner.close();
    }
}