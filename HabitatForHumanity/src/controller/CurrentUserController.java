package controller;

import model.Admin;
import model.Clerk;
import model.Data;
import model.Identity;
import model.ReadOnly;
import model.ReadOnlyPrivs;
import model.User;

public class CurrentUserController {
	private static User u;
	private static String rank;

	public static void set(Identity i) {
		u = Data.getCopyUser(i);
		if (u instanceof Admin) {
			rank = "Admin";
		} else if (u instanceof Clerk) {
			rank = "Clerk";
		} else if (u instanceof ReadOnly) {
			rank = "ReadOnly";
		} else {
			rank = "error";
		}
	}
	
	public static User getUser() {
		return u;
	}
	
	public static Identity getID() {
		return u.getKey();
	}

	public static void logout() {
		u = null;
		rank = null;
	}

	public static Admin userIsAdmin() {
		if (rank.equals("Admin")) {
			return (Admin) u;
		}
		return null;
	}

	public static Clerk userIsClerk() {
		if (rank.equals("Clerk")) {
			return (Clerk) u;
		}
		return null;
	}

	public static ReadOnly userIsReadOnly() {
		if (rank.equals("ReadOnly")) {
			return (ReadOnly) u;
		}
		return null;
	}
	public static ReadOnlyPrivs userViewsItemList() {
		if(rank.equals("error")) {
			return null;
		}
		return (ReadOnlyPrivs) u;
		
	}

	public static String getRank() {
		return rank;
	}
	
	public static boolean checkPass(String password) {
		return u.verifyPass(password);
	}
	
	public static void setPassword(String password) {
		u.setPassword(password);
	}

}
