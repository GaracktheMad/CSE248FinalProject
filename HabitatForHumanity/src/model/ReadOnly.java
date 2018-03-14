package model;

import java.util.ArrayList;

public class ReadOnly extends User implements ReadOnlyPrivs {
	public ReadOnly(String em, String pass) {
		super(em, pass, "U");
	}

	protected ReadOnly(User r, Identity key) {
		super(r.getEmail(), r.getPassword(), key);
	}

	@Override
	public ArrayList<String> details() {
		ArrayList<String> x = preDetails();
		x.add("ReadOnly");
		return x;
	}

}
