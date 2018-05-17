package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

public abstract class User implements Stringable, Serializable, IsListable, CanOrder {

	private static final long serialVersionUID = -1708268517670932602L;
	private String email;
	private final Identity id;
	private static int idNum = 0;
	private String password;
	private LinkedList<Identity> orders;

	public User(String em, String pass, String classifier) {
		email = em;
		password = pass;
		id = new Identity(classifier, ++idNum);
		orders = new LinkedList<Identity>();
	}

	protected User(String em, String pass, Identity i, LinkedList<Identity> order) {
		email = em;
		password = pass;
		id = i;
		orders = order;
	}

	public void addOrder(Identity order) throws InvalidClassificationException {
		if (order.getClassification().equalsIgnoreCase("O") == false) {
			throw new InvalidClassificationException();
		}
		orders.add(order);
	}

	public Iterator<Identity> getOrders() {
		return orders.iterator();
	}

	protected LinkedList<Identity> getOrderList() {
		return orders;
	}

	void removeOrder(Identity order) {
		orders.remove(order);
	}

	public String getName() {
		return email;
	}

	public void setName(String email) {
		this.email = email;
	}

	public boolean verifyPass(String attempt) {
		if (attempt.equals(password)) {
			return true;
		}
		return false;
	}

	String getPassword() {
		return password;
	}

	public void setPassword(String pass) {
		password = pass;
	}

	public Identity getKey() {
		return id;
	}

	public String getID() {
		return id.toString();
	}

	/**
	 * 
	 * @return Stack with id at bottom then email
	 */
	protected Stack<String> preDetails() {
		Stack<String> x = new Stack<String>();
		x.push(id.toString());
		x.push(email);
		return x;
	}

	public ArrayList<IsListable> listAllMyOrders() {
		Iterator<Identity> keys = orders.iterator();
		ArrayList<IsListable> list = new ArrayList<IsListable>();
		while (keys.hasNext()) {
			Identity next = keys.next();
			list.add(new Order(Data.getOrder(next), next));
		}
		return list;
	}

	static int getCounterState() {
		return idNum;
	}

	static void setCounterState(int i) {
		idNum = i;
	}

	public boolean noOrders() {
		return orders.isEmpty();
	}

	public boolean cancelMyOrder(Order o) {
		if (o.getPerson() != id) {
			return false;
		}
		return Data.removeOrder(o);
	}

}
