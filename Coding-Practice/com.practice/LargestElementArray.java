package com.practice;

import java.util.Scanner;

public class LargestElementArray {

    public static int findLargest(int[] arr) {

        int max = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        return max;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int n = 0;

        // Read size safely using nextLine
        while (true) {
            System.out.println("Enter size of array:");

            String input = scanner.nextLine();

            try {
                n = Integer.parseInt(input);

                if (n > 0) {
                    break;
                } else {
                    System.out.println("Size must be greater than 0.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }

        int[] arr = new int[n];

        System.out.println("Enter array elements:");

        for (int i = 0; i < n; i++) {

            while (true) {
                String input = scanner.nextLine();

                try {
                    arr[i] = Integer.parseInt(input);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input! Enter a number:");
                }
            }
        }

        int largest = findLargest(arr);

        System.out.println("Largest element: " + largest);

        scanner.close();
    }
}