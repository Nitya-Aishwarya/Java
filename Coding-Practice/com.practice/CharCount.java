package com.practice;

import java.util.Scanner;

public class CharCount {

    public void printCharCount(String word) {

        int[] lowerFreq = new int[26];
        int[] upperFreq = new int[26];

        for (int i = 0; i < word.length(); i++) {

            char character = word.charAt(i);

            // lowercase
            if (character >= 'a' && character <= 'z') {
                lowerFreq[character - 'a']++;
            }

            // uppercase
            else if (character >= 'A' && character <= 'Z') {
                upperFreq[character - 'A']++;
            }
        }

        System.out.println("Lowercase counts:");

        for (int i = 0; i < 26; i++) {

            if (lowerFreq[i] > 0) {
                System.out.println(
                    (char)('a' + i) + " : " + lowerFreq[i]
                );
            }
        }

        System.out.println("\nUppercase counts:");

        for (int i = 0; i < 26; i++) {

            if (upperFreq[i] > 0) {
                System.out.println(
                    (char)('A' + i) + " : " + upperFreq[i]
                );
            }
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a string:");

        String word = scanner.nextLine();

        CharCount charCount = new CharCount();

        charCount.printCharCount(word);

        scanner.close();
    }
}