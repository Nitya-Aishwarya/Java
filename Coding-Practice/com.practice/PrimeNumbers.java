package com.practice.programs;


class PrintPrime{
	static boolean isPrime(int num) {
		if(num<=1) 
			return false;
		for(int i=2;i<=Math.sqrt(num);i++) {
			if(num%i==0) {
				return false;
			}
		}
		return true;
	}
	
	
	static void printPrimeNumbers(int range) {
		for(int i=0;i<=range;i++) {
			if(isPrime(i)) {
				System.out.println(i);
			}
		}
	}
}
public class PrimeNumbers {
	public static void main(String[] args) {
		PrintPrime.printPrimeNumbers(20);
	}
}
