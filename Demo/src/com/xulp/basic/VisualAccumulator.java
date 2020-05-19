package com.xulp.basic;

import edu.princeton.cs.algs4.StdDraw;

/**
 * 数据可视化工具类
 * @author xulp
 *
 */
public class VisualAccumulator {

	private int N;//输入数据的总的数量
	private double total;//输入数据的总的值
	public VisualAccumulator(int trials, double max) {
		super();
		StdDraw.setXscale(0, trials);
		StdDraw.setYscale(0, max);
		StdDraw.setPenRadius(.005);
	}
	
	public void addDataValue(double val)
	{
		N++;
		total += val;
		StdDraw.setPenColor(StdDraw.DARK_GRAY);
		StdDraw.point(N, val);
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.point(N, total/N);
	}
	
}
