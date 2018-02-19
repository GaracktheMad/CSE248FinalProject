package model;

public class User {
	private String email;
	private String id;
	private static long idNum = 0;
	private String password;
	
	
	
	private void genID() {
		id = String.format("%s#%19d", email.substring(0,6), ++idNum);
	}

	public String getId() {
		return id;
	}

}