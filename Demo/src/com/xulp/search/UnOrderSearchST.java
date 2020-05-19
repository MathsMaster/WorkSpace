package com.xulp.search;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

import com.xulp.bean.Node;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.SequentialSearchST;

/**
 * 基于无序链表构建的符号表
 * 在将数据存储成符号表时,需要的比较次数为N^2/2,效率太低
 * @author xulp
 */
public class UnOrderSearchST<Key,Value>{

	private Node first;
	private int num;
	
	public Value get(Key key)
	{
		if(first == null)
			return null;
		for(Node x = first; x != null ; x = x.getNext())
		{
			if(key.equals(x.getKey()))
				return (Value) x.getValue();
		}
		return null;
	}
	
	//顺便返回查找次数
	public int put(Key key,Value value)
	{
		int n = 0;
		for(Node x = first; x != null ; x = x.getNext())
		{
			if(key.equals(x.getKey()))
			{
				n++;
				x.setValue(value);
				return n;//保证唯一性
			}
			n++;
		}
		//如果未命中
		first = new Node(key,value,first);
		num++;
		return n;
	}
	
	public boolean contains(Key key)
	{
		if(first == null)
			return false;
		for(Node x = first; x != null ; x = x.getNext())
		{
			if(key.equals(x.getKey()))
				return true;
		}
		return false;
	}
	
	public List<Key> keys()
	{
		List<Key> keys = new ArrayList<Key>();
		for(Node x = first; x != null ; x = x.getNext())
		{
			keys.add((Key) x.getKey());
		}
        return keys;
	}
	
}
