package model;

import java.io.Serializable;

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
	protected Item(String n, double p, Source s, Identity i) {
		name = n;
		price = p;
		id = i;
		donator = s;
	}
	
	public Item(Item i) {
		super();
		this.name = i.getName();
		price = i.getPrice();
		donator = i.getDonator();
		id = new Identity("i", ++idCount);
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

	public String[] details() {
		String[] y = donator.details();
		String[] x = new String[2 + y.length];
		x[0] = name;
		x[1] = String.valueOf(price);
		for(int i = 2; i < y.length; i++){
				x[i] = y[i - 2];
		}
		return x;
	}
}
