package model;

import java.io.Serializable;

public class Identity implements Serializable{
	private static final long serialVersionUID = -4714042029386679085L;
	private String classification;
	private final int serial;
	
	public Identity(String classification, int serial) {
		super();
		this.classification = classification.toUpperCase();
		this.serial = serial;
	}
	public String getClassification() {
		return classification;
	}
	public void setClassification(String classification) {
		this.classification = classification.toUpperCase();
	}
	@Override
	public String toString() {
		return String.format("%s%010d", classification, serial);
	}
	
	@Override
	public int hashCode() {
		return serial;
	}
	
	@Override 
	public boolean equals(Object o) {
		if(hashCode() == o.hashCode() && o.getClass() == getClass()) {
			return true;
		}
		return false;
	}

}
