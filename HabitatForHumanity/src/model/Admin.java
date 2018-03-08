package model;

public class Admin extends User implements ClerkPrivs, AdminPrivs, ReadOnlyPrivs {

	Admin(String em, String pass) {
		super(em, pass, "A");
		// TODO Auto-generated constructor stub
	}

	protected Admin(String email, String password, Identity key) {
		super(email, password, key);
	}

	@Override
	public String[] details() {
		// TODO Auto-generated method stub
		return null;
	}




}
