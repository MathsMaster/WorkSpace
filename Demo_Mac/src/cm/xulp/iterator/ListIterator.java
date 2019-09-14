package cm.xulp.iterator;

import java.util.Iterator;

import com.xulp.bean.Node;

public class ListIterator<Item> implements Iterator<Item>{
	
	private Node<Item> current;
	public ListIterator(Node<Item> first) {
		super();
		this.current = first;
	}

	@Override
	public boolean hasNext() {
		return current != null;
	}

	@Override
	public Item next() {
		Item item = current.item;
		current = current.next;
		return item;
	}

}
