package model;

import java.io.Serializable;
import java.util.Stack;

public class Item implements Serializable, Stringable, IsListable {

	private static final long serialVersionUID = -3775776854105022190L;
	private String name;
	private double price;
	private final Identity id;
	private static int idNum = 0;
	private String photoLocation;
	private int quantity;

	public Item(String name, double price, String photoLocation, int amount) {
		super();
		this.name = name;
		this.price = price;
		this.photoLocation = photoLocation;
		id = new Identity("I", ++idNum);
		quantity = amount;
	}

	public Item(Item i) {
		super();
		this.name = i.getName();
		price = i.getPrice();
		id = new Identity("I", ++idNum);
	}

	protected Item(Item item, Identity i) {
		super();
		name = item.getName();
		price = item.getPrice();
		photoLocation = item.getPhotoLocation();
		id = i;
		quantity = item.getQuantity();
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

	public String getName() {
		return name;
	}

	public Identity getKey() {
		return id;
	}

	public String getID() {
		return id.toString();
	}

	@Override
	/**
	 * @return Stack identity, then price, then name, then quantity
	 */
	public Stack<String> details() {
		Stack<String> x = new Stack<String>();
		x.push(String.valueOf(quantity));
		x.push(name);
		x.push(String.valueOf(price));
		x.push(id.toString());
		return x;
	}

	static int getCounterState() {
		return idNum;
	}

	static void setCounterState(int i) {
		idNum = i;
	}

	public String getPhotoLocation() {
		return photoLocation;
	}

	public void setPhoto(String photoLocation) {
		this.photoLocation = photoLocation;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
