package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Hashtable;
import java.util.Iterator;

public class Data {
	private static Hashtable<Identity, User> users = new Hashtable<Identity, User>();
	private static Hashtable<Identity, Item> items = new Hashtable<Identity, Item>();

	public static void init() {
		if (users.isEmpty() == true) {
			Admin a = new Admin("Admin", "Password");
			users.put(a.getKey(), a);
		}
	}

	public static boolean noItems() {
		return items.isEmpty();
	}

	public static boolean noUsers() {
		return users.isEmpty();
	}

	static void addUser(User u) {
		users.put(u.getKey(), u);
		try {
			save();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void createAccount(ReadOnly r) {
		users.put(r.getKey(), r);
		try {
			save();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static void addItem(Item i) {
		items.put(i.getKey(), i);
		try {
			save();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static boolean removeItem(Identity i) {
		if (items.remove(i) == null) {
			return false;
		}
		try {
			save();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	static boolean removeUser(Identity i) {
		if (users.remove(i) == null) {
			return false;
		}
		try {
			save();
		} catch (IOException e) {
			e.printStackTrace();
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

	public static void save() throws FileNotFoundException, IOException {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(new File("Data.bin")));
		out.writeObject(users);
		out.writeInt(User.getCounterState());
		out.writeObject(items);
		out.writeInt(Item.getCounterState());
		out.close();
	}

	@SuppressWarnings("unchecked")
	public static boolean load() {
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(new File("Data.bin")));
			users = (Hashtable<Identity, User>) in.readObject();
			System.out.println(users.get(new Identity("A", 0000000001)).getPassword());
			User.setCounterState(in.readInt());
			System.out.println(User.getCounterState());
			items = (Hashtable<Identity, Item>) in.readObject();
			Item.setCounterState(in.readInt());
			in.close();
			return true;
		} catch (IOException e) {
			return false;
		} catch (ClassNotFoundException e) {
			return false;
		}
	}

	public static Item getCopyItem(Identity key) {
		Item i = items.get(key);
		return new Item(i, key);
	}

	public static int changePassword(Identity key, String original, String newPass, String repeat) {
		if(checkUserKey(key) == false) {
			return -2;
		}
		User u = users.get(key);
		if (newPass.equals(repeat) == false) {
			return 0;
		} else if (u.verifyPass(original) == true) {
			u.setPassword(newPass);
			return 1;
		} else {
			return -1;
		}		
	}

}
