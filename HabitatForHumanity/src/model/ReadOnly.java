package model;


public class ReadOnly extends User implements ReadOnlyPrivs{
	ReadOnly(String em, String pass) {
		super(em, pass, "U");
	}

	@Override
	public String[] details() {
		// TODO Auto-generated method stub
		return null;
	}


}
