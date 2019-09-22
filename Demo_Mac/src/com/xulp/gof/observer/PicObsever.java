package com.xulp.gof.observer;

public class PicObsever implements Observer{

	@Override
	public void update(NumberGenerator o) {
		System.out.println("PicObsever ----> num : "+o.getNum());
	}
}
