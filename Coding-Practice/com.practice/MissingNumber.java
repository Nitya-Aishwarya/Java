package com.practice.programs;

public class MissingNumber {
	public static void main(String[] args) {
		int[] array= {1,2,3,5};
		int xor1 = 0,xor2=0;
		for(int i=0;i<array.length-1;i++) {
			 xor1=array[i]^xor1;
		}
		
		
		for(int i=1;i<array.length+1;i++) {
			xor2=i^xor2;
		}
		
		System.out.println("Missing number:"+(xor2^xor1));
	}
}
