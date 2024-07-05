package com.mine.demo;

public class Demo {

	public static void main(String [] args)
	{
		String matchedWord = "abcing";
		matchedWord = matchedWord.substring(0, matchedWord.length()-3);
		System.out.println(matchedWord);
		matchedWord = "unabcd";
		matchedWord = matchedWord.substring(2, matchedWord.length());
		System.out.println(matchedWord);

		matchedWord = "asdEDF";
//		matchedWord = matchedWord.toLowerCase();
		matchedWord = matchedWord.toUpperCase();
		System.out.println(matchedWord);
	}
}
