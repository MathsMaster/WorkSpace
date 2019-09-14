package com.xulp.sort;

//≤Â»Î≈≈–Ú
public class InsertSort {

	public static int[] insertSort(int [] arrays)
	{
		for(int i  = 0 ; i < arrays.length;i++)
		{
			for(int j = i;j > 0 && arrays[j] < arrays[j-1];j--)
			{
				exchange(arrays, j, j-1);
			}
		}
		return arrays;
	}
	
	static void exchange(int []array,int i,int j)
	{
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
}
