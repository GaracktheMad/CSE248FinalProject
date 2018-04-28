package model;

import java.util.HashMap;
import java.util.Iterator;

public class Data {
	private final static HashMap<Identity, User> users = new HashMap<Identity, User>();
	private final static HashMap<Identity, Item> items = new HashMap<Identity, Item>();

	static void addUser(User u) {
		users.put(u.getKey(), u);
	}

	public static void createAccount(ReadOnly r) {
		users.put(r.getKey(), r);
	}

	static void addItem(Item i) {
		items.put(i.getKey(), i);
	}

	static boolean removeItem(Identity i) {
		if (items.remove(i) == null) {
			return false;
		}
		return true;
	}

	static boolean removeUser(Identity i) {
		if (users.remove(i) == null) {
			return false;
		}
		return true;
	}

	static User getUser(Identity key) {
		if (users.isEmpty()) {
			Admin a = new Admin("example@email.com", "Password");
			users.put(a.getKey(), a);
		}
		return users.get(key);
	}

	public static User getCopyUser(Identity key) {
		User u = users.get(key);
		if (u == null) {
			return null;
		} else if (u instanceof Admin) {
			return new Admin(u, key);
		} else if (u instanceof Clerk) {
			return new Clerk(u, key);
		} else if (u instanceof ReadOnly) {
			return new ReadOnly(u, key);
		} else {
			return null;
		}
	}

	static Item getItem(Identity key) {
		return items.get(key);
	}

	public static Iterator<Identity> getItemKeys() {
		return items.keySet().iterator();
	}

	public static Iterator<Identity> getUserKeys() {
		return users.keySet().iterator();
	}

	public static boolean checkUserKey(Identity key) {
		return users.containsKey(key);
	}

	public static boolean checkItemKey(Identity key) {
		return items.containsKey(key);
	}

}
