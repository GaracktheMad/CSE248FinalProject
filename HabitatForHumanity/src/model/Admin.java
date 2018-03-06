package model;

public class Admin extends User implements ClerkPrivs, AdminPrivs, ReadOnlyPrivs {

	Admin(String em, String pass) {
		super(em, pass, "A");
		// TODO Auto-generated constructor stub
	}

	@Override
	public String[] details() {
		// TODO Auto-generated method stub
		return null;
	}




}
