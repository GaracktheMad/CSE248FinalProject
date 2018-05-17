package model;

import java.util.Stack;

public class Clerk extends User implements ReadOnlyPrivs, ClerkPrivs, OrderManipulationPrivs {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1942255796168243014L;

	public Clerk(String em, String pass) {
		super(em, pass, "C");
	}

	public Clerk(User c, Identity key) {
		super(c.getName(), c.getPassword(), key, c.getOrderList());
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
