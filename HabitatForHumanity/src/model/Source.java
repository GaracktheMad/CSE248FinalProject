package model;

import java.io.Serializable;
import java.util.Stack;

public class Source implements Serializable, Stringable {

	private static final long serialVersionUID = 8807867055473391012L;

	private String address;
	private String name;
	private PhoneNumber contact;

	public Source(String address, String name, PhoneNumber contact) {
		super();
		this.address = address;
		this.name = name;
		this.contact = contact;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PhoneNumber getContact() {
		return contact;
	}

	public void setContact(PhoneNumber contact) {
		this.contact = contact;
	}

	/**
	 * @return Stack with name at bottom, then address, then phone number
	 */
	@Override
	public Stack<String> details() {
		Stack<String> x = new Stack<String>();
		x.push(name);
		x.push(address);
		x.push(contact.toString());
		return x;
	}

}