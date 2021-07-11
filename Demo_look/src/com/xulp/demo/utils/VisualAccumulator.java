package com.xulp.demo.utils;

import edu.princeton.cs.algs4.StdDraw;

/**
 * 数据可视化工具类
 * @author xulp
 *
 */
public class VisualAccumulator {

	private int N;//输入数据的总的数量
	private double total;//输入数据的总的值
	private int max;
	private int avg;
	public VisualAccumulator(int trials, double max,int avg) {
		super();
		this.max = (int) max;
		this.avg = avg;
		StdDraw.setXscale(0, trials);
		StdDraw.setYscale(0, max);
		StdDraw.setPenRadius(.01);
	}
	
	public void addDataValue(double val)
	{
		N++;
		total += val;
		StdDraw.setPenColor(StdDraw.DARK_GRAY);
		StdDraw.point(N, val);
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.point(N, total/N);
		
		StdDraw.setPenColor(StdDraw.ORANGE);
		StdDraw.point(N, max/2);
		StdDraw.point(N, max/4);
		StdDraw.point(N, max*3/4);
		
		StdDraw.setPenColor(StdDraw.GREEN);
		StdDraw.point(N, avg);
	}
	
}
