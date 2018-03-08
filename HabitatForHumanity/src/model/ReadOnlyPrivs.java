package model;

import java.util.Iterator;
import java.util.Stack;

public interface ReadOnlyPrivs {
	public default Stack<Item> listAll() {
		Iterator<Identity> keys = Data.getItemKeys();
		Stack<Item> list = new Stack<Item>();
		while(keys.hasNext()) {
			list.push(Data.getItem(keys.next()));
		}
		return list;
	}


}
