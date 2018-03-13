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

	static boolean editUser(User old, User updated) {
		if (updated.getClass() == Admin.class) {
			return users.replace(old.getKey(), old, new Admin(updated.getEmail(), updated.getPassword(), old.getKey()));
		}
		if (updated.getClass() == ReadOnly.class) {
			return users.replace(old.getKey(), old,
					new ReadOnly(updated.getEmail(), updated.getPassword(), old.getKey()));
		}
		if (updated.getClass() == Clerk.class) {
			return users.replace(old.getKey(), old, new Clerk(updated.getEmail(), updated.getPassword(), old.getKey()));
		}
		return false;
	}

	static boolean editItem(Item old, Item updated) {
		return items.replace(old.getKey(), old,
				new Item(updated.getName(), updated.getPrice(), updated.getDonator(), old.getKey()));
	}

	static User getUser(Identity key) {
		return users.get(key);
	}

	static Item getItem(Identity key) {
		return items.get(key);
	}

	static Iterator<Identity> getItemKeys() {
		return items.keySet().iterator();
	}

	static Iterator<Identity> getUserKeys() {
		return users.keySet().iterator();
	}

}
