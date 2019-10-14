package com.xulp.bean;

import com.xulp.interfac.PQ;

/**
 * 构建一个索引优先队列(大顶堆)
 * @author xulp
 */
public class IndexPQ implements PQ{

	private int [] pq;
	private int [] qp;
	private int [] keys;
	private int n;//表示元素的数量
	
	public IndexPQ(int maxN)
	{
		pq = new int[maxN + 1];
		qp = new int[maxN + 1];
		keys = new int[maxN + 1];
		
		for(int i = 0 ; i < keys.length;i++)
		{
			keys[i] = -1;
		}
	}
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return n;
	}


	@Override
	public boolean isEmpty(int i) {
		// TODO Auto-generated method stub
		return keys[i] != -1;
	}

	@Override//插入一个新元素
	public void insert(int i, int v) {
		if(keys[i] != -1)
			return;
		n++;
		keys[i] = v;//赋值
		pq[n] = i;//keys中的索引对应上pq
		qp[i] = n;//将keys的索引和pq的索引对应上，方便等下的查找
		//因为是个大顶堆,所以对数据进行上浮操作
		swim(n);//因为每次添加时,在pq上的值一直在向后添加，所以这里只需要进行上浮操作
	}

	@Override//修改原有的一个元素
	public void change(int i, int v) {
		keys[i] = v;
		swim(qp[i]);//qp在这里才有些用处了
		sink(qp[i]);
	}

	@Override//上浮或者是下沉后,需要将对应的索引进行更换
	public void exchange(int i, int j) {
		//更改时，不变动keys上的值
		int temp = pq[i];
		pq[i] = j;
		pq[j] = i;
		//需要将key,pq的索引进行相互绑定
		qp[pq[i]] = i;
		qp[pq[j]] = j;
	}

	@Override//传进来的是pq数组上的索引
	public boolean less(int i, int j) {
		return keys[pq[i]] < keys[pq[j]];
	}

	@Override//传进来的是pq上的索引
	public void swim(int k) {
		while(k > 1 && less(k/2, k))
		{
			exchange(k/2, k);
			k = k/2;
		}
	}

	@Override//这里是大顶堆，更小的值才需要下沉
	public void sink(int k) {
		while (2*k <= n) {
            int j = 2*k;
            if (j < n && !less(j, j+1)) j++;
            if (!less(k, j)) break;
            exchange(k, j);
            k = j;
        }
		
	}

}
