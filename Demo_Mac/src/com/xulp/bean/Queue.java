package com.xulp.bean;

import java.util.Iterator;

import cm.xulp.iterator.ListIterator;

public class Queue<Item> implements Iterable<Item>{
	
	private Node<Item> first;
	private Node<Item> last;
	private int num;
	
	public Queue() {
		super();
	}

	public int size()
	{
		return num;
	}
	
	public boolean isEmpty()
	{
		return first == null;
	}
	//添加一个元素
	public void enqueue(Item t)
	{
		Node<Item> node = new Node<Item>();
		node.item = t;
		if(first == null)
		{
			first = node;
			last = first;
		}else {
			last.next = node;
			last = node;
		}
		num++;
	}
	
	//删除最早添加的元素
	public Item dequeue()
	{
		Item item = first.item;
		first = first.next;
		num--;
		return item;
	}

	@Override
	public Iterator<Item> iterator() {
		return new ListIterator<Item>(first);
	}
	
}
