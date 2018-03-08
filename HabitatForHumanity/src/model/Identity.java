package model;

public class Identity {
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
		return String.format("%s-%10d", classification, serial);
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
