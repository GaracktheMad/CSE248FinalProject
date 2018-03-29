package model;

import java.util.Stack;

public class Clerk extends User implements ReadOnlyPrivs, ClerkPrivs {

	public Clerk(String em, String pass) {
		super(em, pass, "C");
	}

	protected Clerk(User c, Identity key) {
		super(c.getEmail(), c.getPassword(), key);
	}

	/**
	 * @see model.User#preDetails()
	 * @return Stack with predetails on bottom with "Clerk" at top
	 */
	@Override
	public Stack<String> details() {
		Stack<String> x = preDetails();
		x.push("Clerk");
		return x;
	}

}
