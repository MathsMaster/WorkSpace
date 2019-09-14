package com.xulp.sort;

public class MergeSort {

	public static int [] yuandiMerge(int [] array)
	{
		int i = 0,mid = array.length / 2 ,hi = array.length - 1;
		int j = mid + 1;
		int [] copyArray = new int[array.length];
		for(int k = 0; k < array.length ; k++)
		{
			copyArray[k] = array[k];
		}
		
		for(int k = 0; k < array.length ; k++)
		{
			if(i > mid)
			{
				array[k] = copyArray[j++];
			}else if(j > hi)
			{
				array[k] = copyArray[i++];
			}else if(copyArray[i] < copyArray[j])
			{
				array[k] = copyArray[i++]; 
			}else {
				array[k] = copyArray[j++]; 
			}
			
		}
		return array;
	}
}
