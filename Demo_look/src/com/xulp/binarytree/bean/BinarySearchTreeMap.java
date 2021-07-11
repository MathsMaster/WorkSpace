package com.xulp.binarytree.bean;

import com.xulp.demo.bean.BaseMap;

/**
 * 基于二叉查找树构建的符号表
 * 
 * @author xulp
 *
 */
public class BinarySearchTreeMap extends BaseMap<String, Integer> {

	Node root;// 根结点

	@Override
	public Object[] keys() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void put(String key, int num) {
		if (root == null)
		{
			root = new Node();	
			root.key = key;
			root.value = num;
			root.n = 1;
			return;
		}
		Node node = getNode(key, root);
		node.key = key;
		node.value = num;
	}

	// 使用递归的方式，拿到对应的节点，不存在对应的节点的话，创建对应位置的节点并返回
	Node getNode(String key, Node node) {
		if ((node.key + "").compareTo(key) > 0)// 在左子树里
		{
			// 判断左子树是否存在，存在就继续递归，否则就直接创建新的节点并返回
			if (node.leftChild == null) {
				node.leftChild = new Node();
				return node.leftChild;
			} else {
				return getNode(key, node.leftChild);
			}
		} else if ((node.key + "").compareTo(key) < 0)// 在右子树里
		{
			// 判断右子树是否存在，存在就继续递归，否则就直接创建新的节点并返回
			if (node.rightChild == null) {
				node.rightChild = new Node();
				return node.rightChild;
			} else {
				return getNode(key, node.rightChild);
			}
		} else {
			return node;
		}
	}

	Node rank(String key, Node node) {
		if (node == null)
			return node;
		if ((node.key + "").compareTo(key) > 0) {
			return rank(key, node.leftChild);
		} else if ((node.key + "").compareTo(key) < 0) {
			return rank(key, node.rightChild);
		} else {
			return node;
		}
	}

	@Override
	public int get(String key) {
		Node node = rank(key, root);
		if (node == null)
			return 0;
		else
			return node.value;
	}

	@Override
	public boolean contains(String key) {
		// TODO Auto-generated method stub
		return false;
	}

	public class Node {
		String key;
		int value;
		Node leftChild, rightChild;
		int n;// 包括当前节点，所挂载的节点数量
	}
}
