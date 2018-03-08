package model;

public class ReadOnly extends User implements ReadOnlyPrivs{
	public ReadOnly(String em, String pass) {
		super(em, pass, "U");
	}
	protected ReadOnly(String email, String password, Identity key) {
		super(email, password, key);
	}

	@Override
	public String[] details() {
		// TODO Auto-generated method stub
		return null;
	}


}
