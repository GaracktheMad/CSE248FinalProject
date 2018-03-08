package model;

public class Clerk extends User implements ReadOnlyPrivs, ClerkPrivs{

	Clerk(String em, String pass) {
		super(em, pass, "C");
		// TODO Auto-generated constructor stub
	}

	protected Clerk(String email, String password, Identity key) {
		super(email, password, key);
	}

	@Override
	public String[] details() {
		// TODO Auto-generated method stub
		return null;
	}

}
