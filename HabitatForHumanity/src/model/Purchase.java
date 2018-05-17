package model;

import java.io.Serializable;

public class Purchase implements Serializable {
	private static final long serialVersionUID = 1L;
	private final Identity item;
	private int quantity;
	private double price;

	public Purchase(Identity item, int quantity) {
		super();
		this.item = item;
		this.quantity = quantity;
		price = Data.getCopyItem(item).getPrice() * quantity;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Identity getItem() {
		return item;
	}

	public double getPrice() {
		return price;
	}

	@Override
	public String toString() {
		return String.format("%d of item number %s. This has a cost of %.2f%n", quantity, item, price);
	}
}
