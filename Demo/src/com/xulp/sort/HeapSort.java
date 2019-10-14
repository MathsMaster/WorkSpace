package com.xulp.sort;
/**
 * 
 * @author xulp
 * 堆排序的功能
 */
public class HeapSort {
	
	//创建堆容器
	private int n = 0;
	private Comparable[] pq;
	
	//堆中元素节点上浮，元素节点下沉
	public HeapSort(int maxN)
	{
		pq = new Comparable[ maxN + 1];
	}
	
	public void insert(Comparable key)
	{
		pq[++n] = key;
		swim(n);
	}
	
	public void delMax()
	{
		exchange(1,n);
		pq[n--] = 0;
		sink(1);
	}
	
	
	private boolean less(int i,int j)
	{
		return pq[i].compareTo(pq[j]) < 0;
	}
	
	private void exchange(int i,int j)
	{
		Comparable temp = pq[i];
		pq[i] = pq[j];
		pq[j] = temp;
	}
	
	//进行节点的上浮操作
	private void swim(int k)
	{
		while(k > 1 && less(k/2,k))
		{
			exchange(k/2, k);
			k = k/2;
		}
	}
	
	//进行节点的下沉操作
	private void sink(int k)
	{
		while(2*k < n)
		{
			int j = 2*k;
			if(less(j,j+1))
				j++;
			if(less(j,k))
				break;
			exchange(k, j);
			k = j;
		}
	}
	
}
