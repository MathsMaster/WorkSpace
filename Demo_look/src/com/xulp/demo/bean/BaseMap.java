package com.xulp.demo.bean;

/**
 * 符号表的基类
 * @author xulp
 */
public abstract class BaseMap<String,Integer>{

	public abstract void put(String key,int num);
	public abstract int get(String key);
	public abstract boolean contains(String key);
	
	public abstract Object [] keys();
}
