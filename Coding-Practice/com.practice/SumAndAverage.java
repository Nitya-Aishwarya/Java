package com.practice;

import java.util.Scanner;

public class SumAndAverage {

    public static void calculateSumAndAverage(int[] arr) {

        int sum = 0;

        for (int num : arr) {
            sum += num;
        }

        double average = (double) sum / arr.length;

        System.out.println("Sum: " + sum);
        System.out.println("Average: " + average);
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter size of array:");
        int n = Integer.parseInt(scanner.nextLine());

        int[] arr = new int[n];

        System.out.println("Enter array elements:");

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(scanner.nextLine().trim());
        }

        calculateSumAndAverage(arr);

        scanner.close();
    }
}