//package com.practice;
//
//import java.util.Arrays;
//import java.util.Scanner;
//
//
//public class Anagrams {
//	public static boolean checkAnagrams(String word1,String word2){
//		
//		word1=word1.replaceAll("\\s+",""). toLowerCase();
//		word2=word2.replaceAll("\\s+","").toLowerCase();
//		if(word1.length()!=word2.length())
//			return false;
//		
//		char[] word1Char=word1.toLowerCase().toCharArray();
//		char[] word2Char=word2.toLowerCase().toCharArray();
//		
//		Arrays.sort(word1Char);
//		Arrays.sort(word2Char);
//
//		return Arrays.equals(word1Char, word2Char);
//		
//	}
//	public static void main(String[] args) {
//		Scanner scanner=new Scanner(System.in);
//		System.out.println("Enter word 1:");
//		String word1=scanner.nextLine();
//		System.out.println("Enter word 2");
//		String word2=scanner.nextLine();
//		if(checkAnagrams(word1, word2)) {
//			System.out.println("Anagrams");
//		}else {
//			System.out.println("Not anagrams");
//		}
//		scanner.close();
//	}
//}


package com.practice;

import java.util.Scanner;

public class Anagrams {

    public static boolean checkAnagrams(String word1, String word2) {

        // remove spaces + lowercase
        word1 = word1.replaceAll("\\s+", "").toLowerCase();
        word2 = word2.replaceAll("\\s+", "").toLowerCase();

        if (word1.length() != word2.length())
            return false;

        // convert to StringBuilder for easy deletion
        StringBuilder sb = new StringBuilder(word2);

        for (int i = 0; i < word1.length(); i++) {

            char ch = word1.charAt(i);

            int index = sb.indexOf(String.valueOf(ch));

            if (index == -1) {
                return false;
            }

            // remove matched character
            sb.deleteCharAt(index);
        }

        return sb.length() == 0;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter word 1:");
        String word1 = scanner.nextLine();

        System.out.println("Enter word 2:");
        String word2 = scanner.nextLine();

        if (checkAnagrams(word1, word2)) {
            System.out.println("Anagrams");
        } else {
            System.out.println("Not anagrams");
        }

        scanner.close();
    }
}
