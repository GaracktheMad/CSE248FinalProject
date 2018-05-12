package controller;

import model.Admin;
import model.AdminPrivs;
import model.Clerk;
import model.ClerkPrivs;
import model.Data;
import model.Identity;
import model.ReadOnly;
import model.ReadOnlyPrivs;
import model.User;

public class CurrentUserController {
	private static User u;
	private static String rank;

	public static boolean set(Identity key) {
		if(Data.checkUserKey(key) == false) {
			return false;
		}
		u = Data.getCopyUser(key);
		if(!u.getKey().equals(key)) {
			return false;
		}
		if (u instanceof Admin) {
			rank = "Admin";
		} else if (u instanceof Clerk) {
			rank = "Clerk";
		} else if (u instanceof ReadOnly) {
			rank = "ReadOnly";
		} else {
			rank = "error";
		}
		return true;
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

	public static AdminPrivs userIsAdmin() {
		if (u instanceof AdminPrivs) {
			return (AdminPrivs) u;
		}
		return null;
	}

	public static ClerkPrivs userIsClerk() {
		if (u instanceof ClerkPrivs) {
			return (ClerkPrivs) u;
		}
		return null;
	}
	
	public static ReadOnlyPrivs userViewsItemList() {
		if(u instanceof ReadOnlyPrivs) {
			return (ReadOnlyPrivs) u;
		}
		return null;
		
	}

	public static String getRank() {
		return rank;
	}
	
	public static boolean checkPass(String password) {
		return u.verifyPass(password);
	}
	
	public static int setPassword(String original, String newPass, String repeat) {
		return Data.changePassword(u.getKey(), original, newPass, repeat);
	}

}
