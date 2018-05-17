package model;

import java.util.Stack;

public class Admin extends User implements ClerkPrivs, AdminPrivs, ReadOnlyPrivs, OrderManipulationPrivs {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2384896244769850210L;

	public Admin(String em, String pass) {
		super(em, pass, "A");
	}

	public Admin(User a, Identity key) {
		super(a.getName(), a.getPassword(), key, a.getOrderList());
	}
	
	/**
	 * @see model.User#preDetails()
	 * @return Stack with predetails on bottom with "Admin" at top
	 */
	@Override
	public Stack<String> details() {
		Stack<String> x = preDetails();
		x.push("Admin");
		return x;
	}

}
