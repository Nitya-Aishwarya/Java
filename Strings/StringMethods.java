package com.practice.strings;

import java.util.Arrays;

public class StringMethods {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str1="Aishu";
		String str2="Aishwarya";
		System.out.println("Length function:");
		System.out.println("Length:"+str1.length());
		String s=null;
//		System.out.println(s.length());If string is null we cant use any methods on that string.
		s="";
		System.out.println(s.length());
		s=" ";
		System.out.println(s.length());
		System.out.println();
		System.out.println("Isempty function:");
		System.out.println("".isEmpty());
		System.out.println(" ".isEmpty());
		System.out.println();
		System.out.println("isBlank Function:");
		System.out.println("".isBlank());
		System.out.println(" ".isBlank());
		System.out.println();
		System.out.println("charAt Function:");
//		System.out.println("Aishwarya".charAt(15)); Index out of box exception
//		System.out.println("Aishwraya".charAt(-1)); Index -1 out of bounds for length 9
		System.out.println("Aishwarya".charAt(4)); 
		System.out.println();
		System.out.println("comparesTo function");
		System.out.println(str1.compareTo(str2));
		System.out.println("Aishu".compareTo("Aishu"));
		System.out.println("Aishwarya".compareTo("Aishu"));//Ascii values difference of mismatched character
		System.out.println("aishu".compareTo("Aishwarya"));
		System.out.println("abc".compareTo("abcdef"));//Remaining characters count
		System.out.println("abcde".compareTo("abc"));
		System.out.println("aishu".compareTo("Aishu"));
		System.out.println("aishu".compareToIgnoreCase("Aishu"));
		System.out.println();
		System.out.println(str2.concat(" Chitra"));
		System.out.println(str2==null);
//		System.out.println(str2.concat(2));it will only work for strings
		System.out.println();
		System.out.println("contains function");
		System.out.println(str2.contains("warya"));
		String str3="Check";
		System.out.println(str3.contains(""));
		System.out.println(str3.contains(" "));
		System.out.println(str3.contains("Check3"));
		System.out.println(str3.contains("check"));
		System.out.println();
		System.out.println("equals and contentEquals function");
		StringBuffer l=new StringBuffer("level");
		StringBuilder sb = new StringBuilder("level");
		System.out.println(sb.reverse().toString().equals("level"));//true
		//String builder and buffer can't override the equals function so it checks the reference of the objs
		System.out.println(sb.reverse().equals(new StringBuilder("level")));//false
		System.out.println(l.reverse().toString().equals(new StringBuffer("level")));
		//false bcoz string compared with string buffer using equal.equal will not work to check the content here
		System.out.println(l.reverse().toString().contentEquals(new StringBuffer("level")));
		String s1=" Aishu ";
		String s2=" aishu";
		System.out.println("equalsIgnoreCase:"+s1.strip().equalsIgnoreCase(s2.strip()));
		//It will check if they are equal or not without any case sensitivity
		//true bcoz string compared with string buffer using contentEquals.contentEquals() is used to check the content here
		System.out.println();
		System.out.println("Codepoint function");
		s="Movie✌️";
		System.out.println(s.length());//7
		System.out.println(s.codePointCount(0, s.length()));//7
		s="😊";
		System.out.println(s.length());//2
		System.out.println(s.codePointCount(0, s.length()));//1
		s = "Hi 👋";
		System.out.println("Length: " + s.length()); // 5
		System.out.println("Code points: " + s.codePointCount(0, s.length())); // 4

		s = "A😊B";
		System.out.println("Length: " + s.length()); // 4 
		System.out.println("Code points: " + s.codePointCount(0, s.length())); //3
		
		System.out.println("codePointAt(0): " + s.codePointAt(0)); // 'A': 65
		System.out.println("codePointAt(1): " + s.codePointAt(1)); 
		System.out.println("codePointAt(2): " + s.codePointAt(2)); 
		System.out.println("codePointAt(3): " + s.codePointAt(3)); 

		System.out.println("codePointBefore(1): " + s.codePointBefore(1)); // 'A' : 65
		System.out.println("codePointBefore(2): " + s.codePointBefore(2)); // 
		System.out.println("codePointBefore(3): " + s.codePointBefore(3)); // 
		System.out.println("codePointBefore(4): " + s.codePointBefore(4)); // 'B' : 66
		System.out.println();
		System.out.println("Regionmatches function:");
		str1="Aishu";
		str2="Chitra Aishwarya";
        System.out.println("regionMatches: " + str1.regionMatches(0, str2, 7, 4)); // true
        str1="aishu";
		str2="Chitra Aishwarya";
		System.out.println("regionMatches (ignoreCase=false): " + str1.regionMatches(0, str2, 7, 4));// false
        System.out.println("regionMatches (ignoreCase=true): " + str1.regionMatches(true, 0, str2, 7, 4)); // true
        System.out.println(str1.regionMatches(100, str2, 7, 3));//false
        System.out.println(str1.regionMatches(true,0, str2, 7, 10));//If it goes out of bound it will return false
        System.out.println(str1.regionMatches(-1, str2, 7, 3));//false
//        System.out.println(str1.regionMatches(0, null, 0, 3));//null should not be accessed
        System.out.println();
        System.out.println("Startswith and endswith:");
        String company="Endava Solutions";
		System.out.println("company.startsWith(\"Endava\"): "+company.startsWith("Endava"));
		System.out.println("company.startsWith(\"Endva\"): "+company.startsWith("Endva"));
		System.out.println("company.endsWith(\"lutions\"): "+company.endsWith("lutions"));
		System.out.println("company.endsWith(\"ltions\"): "+company.endsWith("ltions"));
		
		System.out.println();
        System.out.println("Index functions:");
		String fruits = "banana";
	    System.out.println("indexOf(\"na\"): " + fruits.indexOf("na")); 
	    System.out.println("lastIndexOf(\"na\"): " + fruits.lastIndexOf("na")); 
	    System.out.println(fruits.indexOf("xy"));      // -1
	    System.out.println(fruits.lastIndexOf("z"));   // -1
	    System.out.println(fruits.indexOf(""));        // 0
	    System.out.println(fruits.lastIndexOf(""));    // 6
	    System.out.println(fruits.indexOf("na", 3));        // 4
	    System.out.println(fruits.lastIndexOf("na", 3));    // 2
	    System.out.println(fruits.indexOf("na", -5));       // 2
	    System.out.println(fruits.lastIndexOf("na", -1));   // -1
	    System.out.println(fruits.indexOf("na", 100));      // -1
	    System.out.println(fruits.lastIndexOf("na", 100));  // 4
	    
	    System.out.println();
        System.out.println("substring and subsequence functions:");
	    System.out.println("substring(1, 4): " + fruits.substring(1, 4)); // "ana"
        System.out.println("subSequence(1, 4): " + fruits.subSequence(1, 4)); // "ana"
        System.out.println("substring full: " + fruits.substring(0, fruits.length())); // "banana"
        System.out.println("substring(3, 3): '" + fruits.substring(3, 3) + "'"); // ""
        String string = "Hello";
        CharSequence subSequence = string.subSequence(0, 5);
        System.out.println(subSequence.subSequence(1, 4));
        subSequence = subSequence.subSequence(1, 4);
        System.out.println(subSequence);
        
	    System.out.println();
        System.out.println("contains functions:");
        String str="banana";
        System.out.println("Contains 'nan': " + str.contains("nan")); // true
        System.out.println("Contains 'apple': " + str.contains("apple")); // false
        System.out.println("Contains 'NAN': " + str.contains("NAN")); // false
        System.out.println("Contains '': " + str.contains("")); // true
        
        System.out.println();
        System.out.println("functions:");
        
        String messy = " \t  Hello Java!!  \n ";

        System.out.println("Original: " + messy );
        System.out.println("toUpperCase(): " + messy.toUpperCase() );
        System.out.println("toLowerCase(): " + messy.toLowerCase() );
        
        System.out.println();
        System.out.println("trim and strip functions:");
        System.out.println("trim(): " + messy.trim() );// removes only ASCII whitespace
        System.out.println("strip(): " + messy.strip() ); // removes all Unicode whitespace
        System.out.println("stripLeading(): " + messy.stripLeading() );
        System.out.println("stripTrailing(): " + messy.stripTrailing() );

        String unicodeSpaces = "\u2005\u2005Hello\u2005\u2005";
        System.out.println("original: " + unicodeSpaces );
        System.out.println("trim(): " + unicodeSpaces.trim() );           
        System.out.println("strip(): " + unicodeSpaces.strip() );         

        System.out.println();
        System.out.println("replace function:");
        String sentence = "Learning java concepts from java basics!";
        System.out.println("replace(\"java\", \"Python\"): " + sentence.replace("java", "Python"));  // replaces all
        System.out.println("replaceFirst(\"java\", \"Python\"): " + sentence.replaceFirst("java", "Python")); // only first
        System.out.println("replaceAll(\"java\", \"Python\"): " + sentence.replaceAll("java", "Python")); // regex, all

        
        String data = "User123, ID456, Code789";
        System.out.println("replaceAll(\"\\\\d\", \"X\"): " + data.replaceAll("\\d", "X")); // each digit
        System.out.println("replaceAll(\"\\\\d+\", \"X\"): " + data.replaceAll("\\d+", "X")); // entire number

        
        String price = "$100.00";
        System.out.println("replace(\"$\", \"Rs.\"): " + price.replace("$", "Rs."));              // normal
        System.out.println("replaceAll(\"\\\\$\", \"Rs.\"): " + price.replaceAll("\\$", "Rs."));  // regex
        
        System.out.println();
        System.out.println("Split,join,concat function:");
        s="Endava,Solutions,Private,Limited";
		String[] words=s.split("[aeiou]");
		for(String ele : words)
		{
			System.out.println(ele);
		}
		words=s.split(",",2);
		for(String ele : words)
		{
			System.out.println(ele);
		}
		words=s.split(",");
		for(String ele : words)
		{
			System.out.println(ele);
		}
		
		System.out.println("Joining words:"+String.join(",", words));
		System.out.println("Concatination:"+s.concat(" is loacted in Bangalore"));
		
		System.out.println();
        System.out.println("tocharArray function:");
		String input = "java python cpp";
        char[] chars = input.toCharArray();
        for(char ele:chars)
        {
        	System.out.println(ele);
        }
        byte[] bytes = input.getBytes();
        for(byte ele:bytes)
        {
        	System.out.println(ele);
        }
        
        System.out.println();
        System.out.println("valueOf function:");
        int number = 2430;
        String strNum = String.valueOf(number);
        System.out.println("valueOf(int): " + strNum); // "2430"
        boolean bool = true;
        System.out.println("valueOf(boolean): " + String.valueOf(bool)); // "true"
        char letter = 'S';
        System.out.println("valueOf(char): " + String.valueOf(letter)); // "S"
        
        System.out.println();
        System.out.println("parseInt function:");
        String numStr = "42";
        int parsed = Integer.parseInt(numStr); // from string to int
        System.out.println("parseInt(\"42\"): " + parsed); // 42

	}

}
