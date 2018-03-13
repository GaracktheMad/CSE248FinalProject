package model;

import java.util.Iterator;
import java.util.Stack;

public interface AdminPrivs {
	public default void createUser(User u) {
		Data.addUser(u);
	}
	public default boolean removeItem(Identity key) {
		return Data.removeUser(key);
	}
	public default Stack<User> listAll() {
		Iterator<Identity> keys = Data.getUserKeys();
		Stack<User> list = new Stack<User>();
		while(keys.hasNext()) {
			list.push(Data.getUser(keys.next()));
		}
		return list;
	}
	public default boolean editName(Identity i, String newName) {
		
		return false;
	}
	
	public default boolean editPass(Identity i, String password) {
		
		return false;
	}

}
