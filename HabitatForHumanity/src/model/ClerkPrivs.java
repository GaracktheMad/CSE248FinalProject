package model;

public interface ClerkPrivs {
	public default void createItem(Item i) {
		Data.addItem(new Item(i, i.getKey()));
	}

	public default boolean changeName(Identity key, String name) {
		if (Data.checkItemKey(key) == false) {
			return false;
		}
		Data.getItem(key).setName(name);
		return true;
	}

	public default boolean changePrice(Identity key, double p) {
		if (Data.checkItemKey(key) == false) {
			return false;
		}
		Data.getItem(key).setPrice(p);
		return true;
	}
	
	public default boolean changeImage(Identity key, String loc) {
		if (Data.checkItemKey(key) == false) {
			return false;
		}
		return Data.getItem(key).setPhoto(loc);		
	}
	
	public default boolean changeQuantity(Identity key, int i) {
		if(Data.checkItemKey(key) == false) {
			return false;
		}
		Data.getItem(key).setQuantity(i);
		return true;
	}

	public default boolean removeItem(Identity key) {
		return Data.removeItem(key);
	}

	public default Item getItem(Identity key) {
		return new Item(Data.getItem(key), key);
	}

}
