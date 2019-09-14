package com.xulp.utils;

public class ArrayUtils {

	public static void printArray(int [] arrays)
	{
		for(int i : arrays)
		{
			System.out.print(" "+i);
		}
		System.out.println();
	}
	
	public static int [] generateArray(int size)
	{
		int [] arrays = new int[size];
		for(int i = 0; i < arrays.length;i++ )
		{
			arrays[i] = (int) (Math.random()*1000000);
		}
		return arrays;
	}
	
	public static int [] generateCommonArray(int size)
	{
		int [] arrays = new int[size];
		for(int i = 0; i < arrays.length;i++ )
		{
			arrays[i] = arrays.length - i;
		}
		return arrays;
	}
}
