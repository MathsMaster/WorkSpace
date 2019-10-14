package com.xulp.interfac;

public interface PQ {

	public int size();
	
	public void insert(int i,int v);
	
	public void change(int i,int v);
	
	public boolean isEmpty(int i);
	
	public void exchange(int i,int j);
	
	public boolean less(int i,int j);
	
	public void swim(int k);
	
	public void sink(int k);
	
}
