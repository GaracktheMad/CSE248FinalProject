package model;

import java.util.HashMap;

public class Data {
	private static HashMap <Identity, User> users = new HashMap<Identity, User>();
	private static HashMap <Identity, Item> items = new HashMap<Identity, Item>();

	public static void addUser(User u){
		users.put(u.getKey(), u);
	}
	public static void addItem(Item i){
		items.put(i.getKey(), i);
	}
	public static void removeItem(Item i){
		items.remove(i.getKey());
	}
	public static void removeUser(User u){
		users.remove(u.getKey());
	}
	public static void editUser(User old, User updated){
		User u = new User(updated.getEmail(), updated.getPassword(), old.getKey());
		users.replace(old.getKey(), old, u);
	}


}
