package com.xulp.demo.utils;

public class Stopwatch {

	private final long start;
	public Stopwatch()
	{
		start = System.currentTimeMillis();
	}
	
	public double elapsedTime()
	{
		long now = System.currentTimeMillis();
		return (now - start);
	}
}
