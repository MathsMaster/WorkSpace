package com.xulp.search;

import com.xulp.bean.Node;

/**
 * 基于二分查找构建的有序符号表 该符号表的缺点就是在将随机的数据，构建成有序的符号表时会很慢
 * 因为是使用的数组，在符号表增大时，数组也需要进行增长，这一点对于大规模数据会很耗时
 * 
 * @author xulp
 *
 */
public class BinarySearchST<Key extends Comparable<Key>,Value extends Comparable<Value>> {
	private Key[] keys;
	private Value[] values;
	private int N;//表示当前键数组和索引数组中的
	public BinarySearchST(int capacity) {
		super();
		keys = (Key[]) new Comparable[capacity];
		values = (Value[]) new Comparable[capacity];
	}
	
	public void put(Key key,Value value)
	{
		int index = rank(key);
		if(index < N && key.compareTo(keys[index]) == 0)//存在该值
		{
			keys[index] = key;
			values[index] = value;
		}
		
		//不存在该值的情况下
		for(int j = N; j > index;j--)
		{
			keys[j] = keys[j-1];
			values[j] = values[j-1];
		}
		keys[index] = key;
		values[index] = value;
		N++;
	}
	
	public Value get(Key key)
	{
		if(N == 0)
			return null;
		int index = rank(key);
		if(index < N && key.compareTo(keys[index]) == 0)//存在该值
			return values[index];
		return null;
	}
	
	public boolean contains(Key key)
	{
		if(N == 0)
			return false;
		int index = rank(key);
		if(index < N && key.compareTo(keys[index]) == 0)//存在该值
			return true;
		return false;
	}

	private int rank(Key key)
	{
		//进行二分查找
		int low = 0;
		int high = N - 1;
		if(high < low) 
			return 0;
		
		while(low <= high)
		{
			int mid = low + (high - low)/2;
			if(keys[mid].compareTo(key) < 0 )
			{
				//目标值在右边
				low = mid + 1;
			}else if(keys[mid].compareTo(key) > 0 )
			{
				//目标值在左边
				high = mid - 1;
			}else {
				return mid;
			}
		}
		
		return low;
	}
	
	
}
