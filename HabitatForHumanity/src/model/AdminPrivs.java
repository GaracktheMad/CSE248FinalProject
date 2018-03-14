package model;

import java.util.Iterator;
import java.util.Stack;

public interface AdminPrivs {
	public default void createUser(User u) {
		if (u instanceof Admin) {
			Data.addUser(new Admin(u, u.getKey()));
		}
		if (u instanceof Clerk) {
			Data.addUser(new Clerk(u, u.getKey()));
		}
		if (u instanceof ReadOnly) {
			Data.addUser(new ReadOnly(u, u.getKey()));
		}
	}

	public default boolean removeUser(Identity key) {
		return Data.removeUser(key);
	}

	public default Stack<User> listAllUsers() {
		Iterator<Identity> keys = Data.getUserKeys();
		Stack<User> list = new Stack<User>();
		while (keys.hasNext()) {
			Identity next = keys.next();
			if (Data.getUser(next) instanceof Admin) {
				list.push(new Admin(Data.getUser(next), next));
			}
			else if (Data.getUser(next) instanceof Clerk) {
				list.push(new Clerk(Data.getUser(next), next));
			}
			else if (Data.getUser(next) instanceof ReadOnly) {
				list.push(new ReadOnly(Data.getUser(next), next));
			}
		}
		return list;
	}

	public default boolean editEmail(Identity key, String email) {
		if (Data.checkUserKey(key) == true) {
			Data.getUser(key).setEmail(email);
			return true;
		}
		return false;
	}

	public default boolean editPass(Identity key, String password) {
		if (Data.checkUserKey(key) == true) {
			Data.getUser(key).setPassword(password);
			return true;
		}
		return false;
	}
	
	

}
