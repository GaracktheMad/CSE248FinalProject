package model;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Stack;

public class Item implements Serializable, Stringable, IsListable {

	private static final long serialVersionUID = -3775776854105022190L;
	private String name;
	private double price;
	private final Identity id;
	private static int idNum = 0;
	private String photoLocation;

	public Item(String name, double price, String photoLocation) {
		super();
		this.name = name;
		this.price = price;
		this.photoLocation = photoLocation;
		id = new Identity("I", ++idNum);
	}

	public Item(Item i) {
		super();
		this.name = i.getName();
		price = i.getPrice();
		id = new Identity("I", ++idNum);
	}

	protected Item(Item item, Identity i) {
		super();
		this.name = item.getName();
		price = item.getPrice();
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
	 * @return Stack price, then name
	 */
	public Stack<String> details() {
		Stack<String> x = new Stack<String>();
		x.push(String.valueOf(price));
		x.push(name);
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

	public boolean setPhoto(String photoLocation) {
		File src = new File(photoLocation);
		File target = new File(getID() + photoLocation.substring(photoLocation.lastIndexOf(".")));
		try {
			Files.copy(src.toPath(), target.toPath(), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			return false;
		}
		return true;
	}
}
