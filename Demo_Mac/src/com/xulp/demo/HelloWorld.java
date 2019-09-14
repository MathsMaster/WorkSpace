package com.xulp.demo;

import java.util.Date;
import java.util.Iterator;

import com.xulp.bean.Queue;
import com.xulp.bean.Stack;
import com.xulp.utils.Stopwatch;

public class HelloWorld {

	public static void main(String[] args) {

		testQueue();
		testStack();
		testAlgor();
	}
	
	static void testQueue()
	{
		String info = "a-b-c-d-e-f-g";
		String [] ss = info.split("-");
		Queue<String> queue = new Queue<String>();
		for(String s : ss)
		{
			queue.enqueue(s);
		}
		System.out.println("queue.size() ---> "+queue.size());
		
		Iterator iterator = queue.iterator();
		while(iterator.hasNext())
		{
			String s = (String) iterator.next();
			System.out.print(s);
		}
	}
	
	static void testStack()
	{
		String info = "a-b-c-d-e-f-g";
		String [] ss = info.split("-");
		Stack<String> stack = new com.xulp.bean.Stack<String>();
		for(String s : ss)
		{
			stack.push(s);
		}
		System.out.println("stack.size() ---> "+stack.size());
		
		stack.push("h");
		System.out.println("stack.size() ---> "+stack.size());
		
		Iterator iterator = stack.iterator();
		while(iterator.hasNext())
		{
			String s = (String) iterator.next();
			System.out.print(s);
		}
		
	}
	
	static int[] randomArray()
	{
		int [] array = new int[8000];
		for(int i = 0 ; i < array.length ; i++)
		{
			array[i] = (int) (Math.random() * 100000);
		}
		return array;
	}
	
	//测试算法性能
	static void testAlgor()
	{
		Stopwatch watch = new Stopwatch();
		int count = 0;
		int [] giantArray = randomArray();
		for(int i = 0 ; i < giantArray.length;i++)
		{
			for(int j = i+1 ; j < giantArray.length;j++)
			{
				for(int k = j+1 ; k < giantArray.length;k++)
				{
					if(giantArray[i] + giantArray[j] + giantArray[k] == 0)
						count++;
				}
			}
		}
		System.out.print("使用的时间 "+watch.elapsedTime());
		System.out.println("三元组和为0的有 ---> "+count);
	}

}
