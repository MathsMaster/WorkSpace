package com.xulp.sort;

public class MergeSort {
	
	static int [] copyArray ;
	
	public static int [] topToBottomMerge(int [] array)
	{
		copyArray = new int[array.length];
		sort(array,0,array.length - 1);
		return array;
	}
	
	public static void sort(int array [] ,int lo,int hi )
	{
		if(lo >= hi)
			return;
		int mid = lo + (hi - lo)/2; 
		sort(array,lo,mid);
		sort(array,mid+1,hi);
		merge(array,lo,mid,hi);
	}
	
	
	public static int [] merge(int [] array,int lo,int mid,int hi)
	{
		for(int k = lo; k <= hi ; k++)
		{
			copyArray[k] = array[k];
		}
		
		int i = lo;
		int j = mid + 1;
		
		for(int k = lo; k <= hi ; k++)
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

	public static int [] merge(int [] array)
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
