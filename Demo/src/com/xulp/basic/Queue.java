package com.xulp.basic;

import java.util.Iterator;

/**
 * 创建一个先进先出队列
 * @author xulp
 *
 */
public class Queue<Item> implements Iterable<Item>{

	private Node first;
	private Node last;
	
	class Node<Item>{
		Item item;
		Node next;
	}

	@Override
	public Iterator<Item> iterator() {
		return null;
	}
}
