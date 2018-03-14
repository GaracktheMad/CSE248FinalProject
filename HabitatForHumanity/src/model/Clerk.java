package model;

import java.util.ArrayList;

public class Clerk extends User implements ReadOnlyPrivs, ClerkPrivs {

	public Clerk(String em, String pass) {
		super(em, pass, "C");
	}

	protected Clerk(User c, Identity key) {
		super(c.getEmail(), c.getPassword(), key);
	}

	@Override
	public ArrayList<String> details() {
		ArrayList<String> x = preDetails();
		x.add("Clerk");
		return x;
	}

}
