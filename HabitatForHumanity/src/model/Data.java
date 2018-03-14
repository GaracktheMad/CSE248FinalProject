package model;

import java.util.HashMap;
import java.util.Iterator;

public class Data {
	private final static HashMap<Identity, User> users = new HashMap<Identity, User>();
	private final static HashMap<Identity, Item> items = new HashMap<Identity, Item>();

	static void addUser(User u) {
		users.put(u.getKey(), u);
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
		return users.get(key);
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
