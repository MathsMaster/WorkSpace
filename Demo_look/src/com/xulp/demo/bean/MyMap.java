package com.xulp.demo.bean;

/**
 * 使用无序链表构建的符号表
 * 
 * @author xulp
 */
public class MyMap extends BaseMap<String, Integer> {

	private Node first;
	private String[] keys;

	@Override
	public void put(String key, int num) {

		if (first == null) {
			first = new Node<>(key, num);
			keys = new String[] { key };
			return;
		}

		Node node = first;
		while (node != null && !node.key.equals(key)) {
			node = node.next;
		} 

		if (node == null) {
			node = new Node<>(key, num);
			node.next = first;
			first = node;

			keys = add(keys, key);
		} else {
			node.value = num;
		}

	}

	@Override // 遍历链表取出对应的值
	public int get(String key) {
		Node node = first;
		while (node != null && !node.key.equals(key)) {
			node = node.next;
		}

		if (node == null) {
			return 0;
		} else {
			return node.value;
		}

	}

	@Override
	public boolean contains(String key) {
		if (keys == null)
			return false;
		for (String s : keys) {
			if (key.equals(key))
				return true;
		}
		return false;
	}

	@Override
	public String[] keys() {
		return keys;
	}

	String[] add(String[] keys, String key) {
		String[] newKeys = new String[keys.length + 1];
		for (int i = 0; i < keys.length; i++) {
			newKeys[i] = keys[i];
		}
		newKeys[newKeys.length - 1] = key;

		return newKeys;
	}

	public class Node<String, Integer> {

		public Node(String key, int value, Node next) {
			super();
			this.key = key;
			this.value = value;
			this.next = next;
		}

		public Node(String key, int value) {
			super();
			this.key = key;
			this.value = value;
		}

		String key;
		int value;
		Node next;
	}

}
