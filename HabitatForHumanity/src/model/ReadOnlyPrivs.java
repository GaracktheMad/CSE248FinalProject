package model;

import java.util.ArrayList;
import java.util.Iterator;

public interface ReadOnlyPrivs {

	public default ArrayList<IsListable> listAllItems() {
		Iterator<Identity> keys = Data.getItemKeys();
		ArrayList<IsListable> list = new ArrayList<IsListable>();
		while (keys.hasNext()) {
			Identity next = keys.next();
			list.add(new Item(Data.getItem(next), next));
		}
		return list;
	}

}
