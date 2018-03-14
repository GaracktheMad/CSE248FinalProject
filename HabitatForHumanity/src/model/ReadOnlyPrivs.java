package model;

import java.util.Iterator;
import java.util.Stack;

public interface ReadOnlyPrivs {

	public default Stack<Item> listAllItems() {
		Iterator<Identity> keys = Data.getUserKeys();
		Stack<Item> list = new Stack<Item>();
		while (keys.hasNext()) {
			Identity next = keys.next();
			list.push(new Item(Data.getItem(next), next));
		}
		return list;
	}

}
