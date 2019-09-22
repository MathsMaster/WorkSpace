package com.xulp.gof.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 被观察者对象
 * @author xulp
 *
 */
public abstract class NumberGenerator {
	List<Observer> list = new ArrayList();
	
	public abstract int getNum();
	
	public void addObserver(Observer o)
	{
		list.add(o);
	}
	
	public void deleteObserver(Observer o)
	{
		list.remove(o);
	}
	
	public void notifyObserverData()
	{
		for(Observer o : list)
		{
			o.update(this);
		}
	}
}
