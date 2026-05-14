package com.practice;

import java.util.Scanner;

public class SubstringToolkit {

    // 1. Count total substrings using formula
    public static int countTotalSubstrings(String str) {
        int n = str.length();
        return n * (n + 1) / 2;
    }

    // 2. Print all substrings
    public static void printAllSubstrings(String str) {

        System.out.println("\nAll substrings:");

        for (int i = 0; i < str.length(); i++) {
            for (int j = i + 1; j <= str.length(); j++) {
                System.out.println(str.substring(i, j));
            }
        }
    }

    // 3. Count substrings of specific length
    public static int countSubstringsOfLength(String str, int k) {
        if (k > str.length()) return 0;
        return str.length() - k + 1;
    }

    // 4. Check if substring exists
    public static boolean containsSubstring(String str, String sub) {
        return str.contains(sub);
    }

    // 5. Longest substring (simple continuous version = whole string here)
    public static String longestSubstring(String str) {
        return str; // (true "longest substring" problems are different)
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a string:");
        String str = scanner.nextLine();

        // 1. Total substrings
        System.out.println("\nTotal substrings: " + countTotalSubstrings(str));

        // 2. Print all substrings
        printAllSubstrings(str);

        // 3. Substrings of length k
        System.out.println("\nEnter length k:");
        int k = scanner.nextInt();
        System.out.println("Substrings of length " + k + ": " + countSubstringsOfLength(str, k));

        scanner.nextLine(); // consume newline

        // 4. Check substring
        System.out.println("\nEnter substring to search:");
        String sub = scanner.nextLine();

        System.out.println("Exists? " + containsSubstring(str, sub));

        scanner.close();
    }
}