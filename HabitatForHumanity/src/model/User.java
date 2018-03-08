package model;

public abstract class User implements Stringable {
	private String email;
	private final Identity id;
	private static int idNum = 0;
	private String password;

	public User(String em, String pass, String classifier){
		email = em;
		password = pass;
		id = new Identity(classifier ,idNum++);
	}

	protected User(String em, String pass, Identity i){
		email = em;
		password = pass;
		id = i;
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

	String getPassword(){
		return password;
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
