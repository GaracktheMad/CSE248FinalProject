package model;

public class User implements Admin, Clerk {
	private String email;
	private String id;
	private static long idNum = 0;
	private String password;
	private Rank rank;
	
	
	
	private void genID() {
		id = String.format("%s#%19d", email.substring(0,6), ++idNum);
	}

	public String getId() {
		return id;
	}

	@Override
	public boolean isClerk() {
		if(rank == ) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isAdmin() {
		// TODO Auto-generated method stub
		return false;
	}

}
