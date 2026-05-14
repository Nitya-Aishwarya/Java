package com.practice;

import java.util.Arrays;
import java.util.Scanner;

public class RemoveDuplicatesSortedArray {

    public static int removeDuplicates(int[] arr) {

        if (arr.length == 0) return 0;

        int i = 0; // index of last unique element

        for (int j = 1; j < arr.length; j++) {

            if (arr[j] != arr[i]) {
                i++;
                arr[i] = arr[j];
            }
        }

        return i + 1; // new length of array
    }
    
    public static void moveZeros(int[] arr) {
    	
    	int i=0;
    	
    	for(int j=0;j<arr.length;j++) {
    		if(arr[j]!=0) {
    			arr[i++]=arr[j];
    		}
    	}
    	
    	while(i<arr.length) {
    		arr[i++]=0;
    	}
    	
    	
    }
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter size of array:");
        int n = Integer.parseInt(scanner.nextLine());

        int[] arr = new int[n];

        System.out.println("Enter sorted array elements:");

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(scanner.nextLine());
        }

        
        moveZeros(arr);
        System.out.println(Arrays.toString(arr));
       
        int newLength = removeDuplicates(arr);

        System.out.println("Array after removing duplicates:");

        for (int i = 0; i < newLength; i++) {
            System.out.print(arr[i] + " ");
        }

        scanner.close();
    }
}