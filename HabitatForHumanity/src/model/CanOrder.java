package model;

import java.io.IOException;
import java.util.ArrayList;

public interface CanOrder {
	public default void createOrder(Order o) throws InvalidClassificationException, InvalidStockException {
		Item i = Data.getItem(o.getItem());
		if (o.getQuantity() > i.getQuantity()) {
			throw new InvalidStockException();
		}
		Data.addOrder(new Order(o, o.getKey()));
		Data.getUser(o.getPerson()).addOrder(o.getKey());
		i.setQuantity(i.getQuantity() - o.getQuantity());
		try {
			Data.save();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<IsListable> listAllMyOrders();

}
