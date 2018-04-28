package model;

import java.util.ArrayList;
import java.util.Iterator;

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

	public default ArrayList<IsListable> listAllUsers() {
		Iterator<Identity> keys = Data.getUserKeys();
		ArrayList<IsListable> list = new ArrayList<IsListable>();
		while (keys.hasNext()) {
			Identity next = keys.next();
			if (Data.getUser(next) instanceof Admin) {
				list.add(new Admin(Data.getUser(next), next));
			}
			else if (Data.getUser(next) instanceof Clerk) {
				list.add(new Clerk(Data.getUser(next), next));
			}
			else if (Data.getUser(next) instanceof ReadOnly) {
				list.add(new ReadOnly(Data.getUser(next), next));
			}
		}
		return list;
	}

	public default boolean editEmail(Identity key, String email) {
		if (Data.checkUserKey(key) == true) {
			Data.getUser(key).setName(email);
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
