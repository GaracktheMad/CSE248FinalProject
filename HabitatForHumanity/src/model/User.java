package model;

public abstract class User {
	private String email;
	private Identity id;
	private static int idNum = 0;
	private String password;
	
	User(String em, String pass, String classifier){
		email = em;
		password = pass;
		id = new Identity(classifier ,idNum++);
	}
	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public boolean verifyPass(String attempt) {
		if(attempt.equals(password)) {
			return true;
		}
		return false;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	public Identity getKey() {
		return id;
	}
	
	public String getID() {
		return id.toString();
	}
	

}
