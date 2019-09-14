package com.xulp.sort;

//希尔排序的原理:将一段数组拆成n段，每段m个元素，取每一段的第一个元素进行插入排序，依次取每段的第二个元素进行排序。。。。。。
//			继续将整个数组拆成2n段，每段2m个元素，依次取每段的第一个元素进行排序，依次取第二个元素进行排序。。。。
//			......
//			继续拆分数组，直至每段只有一个元素，进行插入排序。
public class ShellSort {

	public static int [] shellSort(int [] array)
	{
		int n = 3;//表示分为多少段
		int m = array.length/n;//表示每段元素的数量，最后一段可能只有不到m个元素
		while(m >= 1)
		{
			for(int j = 0 ; j < m ; j++)
			{
				for(int k = j; k < array.length;k += m)
				{
					for(int i = k ; i >= m && array[i] < array[i - m] ; i -= m)
					{
						//对取到的值进行排序调换位置
						exchange(array, i, i - m);
					}
				}
				
			}
			n = 2*n;
			m = array.length/n;
		}
		return array;
	}
	
	static void exchange(int []array,int i,int j)
	{
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
}
