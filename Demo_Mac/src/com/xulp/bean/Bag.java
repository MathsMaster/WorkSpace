package com.xulp.bean;

import java.util.Iterator;

import cm.xulp.iterator.ListIterator;

public class Bag<Item> implements Iterable<Item>{
	
	private Node<Item> first;
	private int num;
	
	public Bag() {
		super();
	}
	
	public void add(Item t)
	{
		Node<Item> node = new Node<Item>();
		node.item = t;
		node.next = first;
		first = node;
		num++;
	}
	
	public int size()
	{
		return num;
	}
	
	public boolean isEmpty()
	{
		return first == null;
	}
	
	@Override
	public Iterator<Item> iterator() {
		return new ListIterator<>(first);
	}
}
