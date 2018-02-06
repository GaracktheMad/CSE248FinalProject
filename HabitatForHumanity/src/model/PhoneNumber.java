package model;

import java.io.Serializable;

public class PhoneNumber implements Serializable {

	private static final long serialVersionUID = -1965750390404703731L;
	private int country = 1;
	private int areaCode;
	private int officePrefix;
	private int lineNumber;
	@Override
	public String toString() {
		return country + "(" + areaCode + ")" + officePrefix + "-" + lineNumber;
	}
	

}
