package com.xulp.utils;

public class Stopwatch {

	private final long start;

	public Stopwatch() {
		super();
		start = System.currentTimeMillis();
	}
	
	public long elapsedTime()
	{
		long now = System.currentTimeMillis();
		return (now - start)/1000;
	}
}
