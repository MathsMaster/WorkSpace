package com.xulp.utils;

public class SeekUtils {

	//二分查找有序数组,进行白名单过滤，即key不存在于array中时返回
	public static int binarySearch(int key,int [] array)
	{ 
		if(array == null || array.length == 0)
			return key;
		int startIndex = 0;
		int endIndex = array.length - 1;
		int midIndex = startIndex + (endIndex - startIndex);
		while(startIndex < endIndex)
		{
			if(key < array[midIndex])
			{
				endIndex = midIndex - 1;
			}else if(key > array[midIndex])
			{
				startIndex = midIndex + 1;
			}else {
				return -1;
			}
		}
		return -1;
	}
	
	//二分查找有序数组,返回对应的索引
	public static int binarySearchIndex(int key,int [] array)
	{
		if(array == null || array.length == 0)
			return -1;
		int startIndex = 0;
		int endIndex = array.length - 1;
		int midIndex = startIndex + (endIndex - startIndex);
		while(startIndex < endIndex)
		{
			if(key < array[midIndex])
			{
				endIndex = midIndex - 1;
			}else if(key > array[midIndex])
			{
				startIndex = midIndex + 1;
			}else {
				return midIndex;
			}
		}
		return -1;
	}
}