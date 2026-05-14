package com.practice.programs;

public class Pattern1 {
	public static void main(String[] args) {
		
		
		for(int i=0;i<5;i++) {
			for(int j=0;j<i+1;j++) {
				System.out.print("*");
			}
			System.out.println();
		}
		
		
		for(int i=0;i<5;i++) {
			for(int j=i;j>=0;j--) {
				System.out.print("*");
			}
//			System.out.println("*");
			
			System.out.println();
		}
	}
}
