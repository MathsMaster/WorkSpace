package com.xulp.demo;

import com.xulp.search.BinarySearch;
import com.xulp.sort.InsertSort;
import com.xulp.sort.MergeSort;
import com.xulp.sort.QuickSort;
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
//		testYuanDiMergeSort();
		testTopToBottomMergeSort();
//		testBinarySearch();
		testQuickSort();
	}
	
	static void testSelectSort()
	{
		int array [] = ArrayUtils.generateArray(1000000);
//		ArrayUtils.printArray(array);
		Stopwatch watch = new Stopwatch();
		SelectSort.selectSort(array);
		System.out.println("耗费时间 : "+watch.elapsedTime());
//		ArrayUtils.printArray(array);
	}
	
	static void testInsertSort()
	{
		int array [] = ArrayUtils.generateArray(1000000);
//		ArrayUtils.printArray(array);
		Stopwatch watch = new Stopwatch();
		InsertSort.insertSort(array);
		System.out.println("耗费时间 : "+watch.elapsedTime());
//		ArrayUtils.printArray(array);
	}
	
	static void testShellSort()
	{
		int array [] = ArrayUtils.generateArray(100000000);
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
		MergeSort.merge(array);
		System.out.println("耗费时间 : "+watch.elapsedTime());
		ArrayUtils.printArray(array);
	}
	static void testTopToBottomMergeSort()
	{
		int array [] = ArrayUtils.generateArray(100000000);
//		ArrayUtils.printArray(array);
		Stopwatch watch = new Stopwatch();
		MergeSort.topToBottomMerge(array);
		System.out.println("耗费时间 : "+watch.elapsedTime());
//		ArrayUtils.printArray(array);
	}
	
	static void testBinarySearch()
	{
		int array [] = ArrayUtils.generateArray(10000000,500000);
		
		MergeSort.topToBottomMerge(array);
//		ArrayUtils.printArray(array);
		Stopwatch watch = new Stopwatch();
		int index = BinarySearch.rank(500000,array);
		System.out.println("耗费时间 : "+watch.elapsedTime()+" index :" + index);

//		ArrayUtils.printArray(
	}
	
	static void testQuickSort()
	{
		int array [] = ArrayUtils.generateArray(100000000);
//		ArrayUtils.printArray(array);
		Stopwatch watch = new Stopwatch();
		QuickSort.sort(array);
		System.out.println("耗费时间 : "+watch.elapsedTime());
//		ArrayUtils.printArray(array);
	}

}
