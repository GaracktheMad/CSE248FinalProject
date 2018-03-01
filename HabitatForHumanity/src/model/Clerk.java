package model;

public class Clerk extends User implements ReadOnlyPrivs, ClerkPrivs{

	Clerk(String em, String pass) {
		super(em, pass, "C");
		// TODO Auto-generated constructor stub
	}

}
