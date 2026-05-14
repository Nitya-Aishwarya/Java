package com.practice;


import java.util.Scanner;

public class UniqueCharacter {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter a string:");
        String s = sc.nextLine();
        s=s.strip().replaceAll("[^A-Za-z0-9]", "");
        char result = findFirstUnique(s);

        if (result != 0) {
            System.out.println("First unique character: " + result);
        } else {
            System.out.println("No unique character found");
        }

        sc.close();
    }

    public static char findFirstUnique(String s) {

        int[] freq = new int[256]; 

        // Step 1: count frequency
        for (char c : s.toCharArray()) {
            freq[c]++;
        }

        // Step 2: find first unique character
        for (char c : s.toCharArray()) {
            if (freq[c] == 1) {
                return c;
            }
        }

        return 0; // indicates no unique character
    }
}