package com.xulp.gof.observer;
/**
 * 测试观察者设计模式
 * @author xulp
 */
public class HelloWorld {

	public static void main(String[] args) {
		
		AINumberGenerator aiGenerator = new AINumberGenerator();
		aiGenerator.increaseNum();
		
		NumberObserver nObserver = new NumberObserver();
		PicObsever pObserver = new PicObsever();
		aiGenerator.addObserver(nObserver);
		aiGenerator.addObserver(pObserver);
		
		
		aiGenerator.increaseNum();
	}

}
