package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public interface OrderManipulationPrivs extends CanOrder {

	public default boolean changeOrdersQuantity(Identity key, int q) {
		if (Data.checkOrderKey(key) == false) {
			return false;
		}
		Data.getOrder(key).setQuantity(q);
		try {
			Data.save();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	public default boolean setStatus(Identity key, boolean isCompleted) {
		if (Data.checkOrderKey(key) == false) {
			return false;
		}
		Data.getOrder(key).setCompleted(isCompleted);
		try {
			Data.save();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	public default boolean cancelOrder(Order o) {
		return Data.removeOrder(o);
	}

	public default ArrayList<IsListable> listAllOrders() {
		Iterator<Identity> keys = Data.getOrderKeys();
		ArrayList<IsListable> list = new ArrayList<IsListable>();
		while (keys.hasNext()) {
			Identity next = keys.next();
			list.add(new Order(Data.getOrder(next), next));
		}
		return list;
	}

}
