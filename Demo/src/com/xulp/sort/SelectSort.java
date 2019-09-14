package com.xulp.sort;

public class SelectSort {
	
	public static int[] selectSort(int [] arrays)
	{
		for(int i  = 0 ; i < arrays.length;i++)
		{
			int min = i;
			for(int j = i+1;j < arrays.length;j++)
			{
				if(arrays[j] < arrays[min]) min = j;
			}
			exchange(arrays, i, min);
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
