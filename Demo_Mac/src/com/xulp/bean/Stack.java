package com.xulp.bean;

import java.util.Iterator;

import cm.xulp.iterator.ListIterator;

/**
 * 上压堆栈
 * @author xulp
 * @param <Item>
 */
public class Stack<Item> implements Iterable<Item>{

	private Node<Item> first;
	private int num;

	public Stack() {
		super();
	}

	public int size() {
		return num;
	}

	public boolean isEmpty() {
		return first == null;
	}

	//添加一个元素
	public void push(Item t) {
		Node<Item> node = new Node<Item>();
		node.item = t;
		node.next = first;
		first = node;
		num++;
	}

	//删除最后添加的元素
	public Item pop() {
		if(first == null)
			return null;
		Item item = first.item;
		first = first.next;
		num--;
		return item;
	}

	@Override
	public Iterator<Item> iterator() {
		return new ListIterator<>(first);
	}
}
