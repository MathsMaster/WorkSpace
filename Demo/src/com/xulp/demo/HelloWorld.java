package com.xulp.demo;

import com.xulp.sort.InsertSort;
import com.xulp.sort.MergeSort;
import com.xulp.sort.SelectSort;
import com.xulp.sort.ShellSort;
import com.xulp.utils.ArrayUtils;
import com.xulp.utils.Stopwatch;

public class HelloWorld {

	public static void main(String[] args) {
		
		System.out.println("Hello World!");
//		testSelectSort();
//		testInsertSort();
//		testShellSort();
		testYuanDiMergeSort();
	}
	
	static void testSelectSort()
	{
		int array [] = ArrayUtils.generateArray(500000);
//		ArrayUtils.printArray(array);
		Stopwatch watch = new Stopwatch();
		SelectSort.selectSort(array);
		System.out.println("耗费时间 : "+watch.elapsedTime());
//		ArrayUtils.printArray(array);
	}
	
	static void testInsertSort()
	{
		int array [] = ArrayUtils.generateArray(500000);
//		ArrayUtils.printArray(array);
		Stopwatch watch = new Stopwatch();
		InsertSort.insertSort(array);
		System.out.println("耗费时间 : "+watch.elapsedTime());
//		ArrayUtils.printArray(array);
	}
	
	static void testShellSort()
	{
		int array [] = ArrayUtils.generateArray(10000000);
//		ArrayUtils.printArray(array);
		Stopwatch watch = new Stopwatch();
		ShellSort.shellSort(array);
		System.out.println("耗费时间 : "+watch.elapsedTime());
//		ArrayUtils.printArray(array);
	}
	
	static void testYuanDiMergeSort()
	{
		int array [] = {1,2,3,4,5,6,10,7,9,8,12,11};
		ArrayUtils.printArray(array);
		Stopwatch watch = new Stopwatch();
		MergeSort.yuandiMerge(array);
		System.out.println("耗费时间 : "+watch.elapsedTime());
		ArrayUtils.printArray(array);
	}

}
