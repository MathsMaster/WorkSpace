package com.xulp.gof.observer;

public class NumberObserver implements Observer{

	@Override
	public void update(NumberGenerator o) {
		System.out.println("NumberObserver ----> num : "+o.getNum());
	}
}
