package com.xulp.sort;

import com.xulp.utils.ArrayUtils;

/**
 * 快速排序
 * @author xulp
 */
public class QuickSort {
	
	public static void sort(int [] array)
	{
		sort(array,0,array.length - 1);
	}
	
	public static void sort(int [] array,int lo, int hi)
	{
		if(lo >= hi)
			return;
		int j = partation(array, lo, hi);
		sort(array,lo,j - 1);
		sort(array,j + 1,hi);
	}
	
	//快速排序中进行拆分的部分
	private static int partation(int [] array,int lo,int hi)
	{
		int i = lo;
		int j = hi + 1;
		int v = array[lo];
		while(true)
		{
			while(array[++i] < v)
			{
				if(i == hi)
					break;
			}
			while(array[--j] > v)
			{
				if(j == lo)
					break;
			}
			if(i >= j) break;
			ArrayUtils.exchange(array, i, j);
		}
		ArrayUtils.exchange(array, lo, j);
		return j;
	}
	
}
