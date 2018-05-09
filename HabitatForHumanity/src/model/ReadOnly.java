package model;

import java.util.Stack;

public class ReadOnly extends User implements ReadOnlyPrivs {
	/**
	 * 
	 */
	private static final long serialVersionUID = -663055128390325143L;

	public ReadOnly(String em, String pass) {
		super(em, pass, "R");
	}

	protected ReadOnly(User r, Identity key) {
		super(r.getName(), r.getPassword(), key);
	}

	/**
	 * @see model.User#preDetails()
	 * @return Stack with predetails on bottom with "ReadOnly" at top
	 */
	@Override
	public Stack<String> details() {
		Stack<String> x = preDetails();
		x.push("ReadOnly");
		return x;
	}

}
