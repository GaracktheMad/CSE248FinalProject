package model;

public interface ClerkPrivs {
	public default void createItem(Item i) {
		Data.addItem(i);
	}
	public default void changeName(Identity key, String name ) {
		Item old = Data.getItem(key);
		Item updated = new Item(old);
		updated.setName(name);
		Data.editItem(old, updated);
	}
	public default void changeSource(Identity key, Source s ) {
		Item old = Data.getItem(key);
		Item updated = new Item(old);
		updated.setDonator(s);
		Data.editItem(old, updated);
	}
	public default void changePrice(Identity key, double p ) {
		Item old = Data.getItem(key);
		Item updated = new Item(old);
		updated.setPrice(p);
		Data.editItem(old, updated);
	}
	public default boolean removeItem(Identity key) {
		return Data.removeItem(key);
	}

}
