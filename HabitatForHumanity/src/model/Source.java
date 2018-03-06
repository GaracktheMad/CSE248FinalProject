package model;

import java.io.Serializable;

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

	@Override
	public String[] details(){
		String[] x = {name, address, contact.toString()};
		return x ;
	}


}