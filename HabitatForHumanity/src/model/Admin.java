package model;

import java.util.ArrayList;

public class Admin extends User implements ClerkPrivs, AdminPrivs, ReadOnlyPrivs {

	public Admin(String em, String pass) {
		super(em, pass, "A");
	}

	protected Admin(User a, Identity key) {
		super(a.getEmail(), a.getPassword(), key);
	}

	@Override
	public ArrayList<String> details() {
		ArrayList<String> x = preDetails();
		x.add("Admin");
		return x;
	}

}
