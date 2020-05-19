package com.xulp.bean;

/**
 * 用于使用链表实现的符号表的对象
 * @author xulp
 *
 */
public class Node<Key,Value> {

	private Key key;
	private Value value;
	private Node next;
	
	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public Value getValue() {
		return value;
	}

	public void setValue(Value value) {
		this.value = value;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	public Node(Key key, Value value, Node next) {
		super();
		this.key = key;
		this.value = value;
		this.next = next;
	}
	
	
}
