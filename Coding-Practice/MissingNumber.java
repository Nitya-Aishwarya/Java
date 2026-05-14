package com.practice;

import java.util.Scanner;

public class MissingNumber {

    public static int findMissingNumber(int[] arr, int n) {

        int expectedSum = n * (n + 1) / 2;
        int actualSum = 0;

        for (int num : arr) {
            actualSum += num;
        }

        return expectedSum - actualSum;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter value of N:");
        int n = Integer.parseInt(scanner.nextLine());

        int[] arr = new int[n - 1];

        System.out.println("Enter " + (n - 1) + " elements:");

        for (int i = 0; i < n - 1; i++) {
            arr[i] = Integer.parseInt(scanner.nextLine());
        }

        int missing = findMissingNumber(arr, n);

        System.out.println("Missing number: " + missing);

        scanner.close();
    }
}