package com.xulp.gof.observer;

public class AINumberGenerator extends NumberGenerator{

	int num = 9;
	
	@Override
	public int getNum() {
		return num;
	}
	
	public void increaseNum()
	{
		num++;
		notifyObserverData();
	}

}
