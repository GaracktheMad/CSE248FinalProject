package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Item implements Serializable, Stringable {

	private static final long serialVersionUID = -3775776854105022190L;
	private String name;
	private double price;
	private final Identity id;
	private static int idCount = 0;
	private Source donator;

	public Item(String name) {
		super();
		this.name = name;
		id = new Identity("i", ++idCount);

	}

	public Item(Item i) {
		super();
		this.name = i.getName();
		price = i.getPrice();
		donator = i.getDonator();
		id = new Identity("i", ++idCount);
	}

	protected Item(Item item, Identity i) {
		super();
		this.name = item.getName();
		price = item.getPrice();
		donator = item.getDonator();
		id = i;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Source getDonator() {
		return donator;
	}

	public void setDonator(Source donator) {
		this.donator = donator;
	}

	public String getName() {
		return name;
	}

	public Identity getKey() {
		return id;
	}

	public String getID() {
		return id.toString();
	}

	public ArrayList<String> details() {
		ArrayList<String> x = new ArrayList<String>();
		x.add(name);
		x.add(String.valueOf(price));
		x.addAll(donator.details());
		return x;
	}
}
