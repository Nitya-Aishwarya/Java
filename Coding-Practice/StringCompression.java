package com.practice;

import java.util.Scanner;

public class StringCompression {

    public static String compress(String str) {

        if (str == null || str.length() == 0)
            return str;

        StringBuilder result = new StringBuilder();

        int count = 1;

        for (int i = 1; i < str.length(); i++) {

            if (str.charAt(i) == str.charAt(i - 1)) {
                count++;
            } else {
                result.append(str.charAt(i - 1));
                result.append(count);
                count = 1;
            }
        }

        result.append(str.charAt(str.length() - 1));
        result.append(count);

        return result.toString();
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a string:");
        String str = scanner.nextLine();

        System.out.println("Compressed string: " + compress(str));

        scanner.close();
    }
}