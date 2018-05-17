package model;

import java.io.Serializable;
import java.util.Stack;

public class Order implements Serializable, Stringable, IsListable {
	private static final long serialVersionUID = -6054199198814140518L;
	private final Identity item;
	private final Identity person;
	private final Identity orderNumber;
	private boolean isCompleted;
	private static int counter = 0;
	private int quantity;
	private double price;

	public Order(Identity item, Identity person, int quantity) {
		super();
		this.item = item;
		this.person = person;
		this.quantity = quantity;
		this.isCompleted = false;
		orderNumber = new Identity("O", ++counter);
		calcPrice();
	}

	protected Order(Order o, Identity i) {
		item = o.getItem();
		person = o.getPerson();
		quantity = o.getQuantity();
		isCompleted = o.isCompleted();
		orderNumber = i;
		calcPrice();
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
		calcPrice();
	}

	public double getPrice() {
		return price;
	}

	private void calcPrice() {
		price = Data.getCopyItem(item).getPrice() * quantity;
	}

	public boolean isCompleted() {
		return isCompleted;
	}

	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

	public Identity getItem() {
		return item;
	}

	public Identity getPerson() {
		return person;
	}

	public String getOrderNumber() {
		return orderNumber.toString();
	}

	/**
	 * @return a stack with Order Number at top, person ID, item ID, quantity,
	 *         price, then status
	 */
	@Override
	public Stack<String> details() {
		Stack<String> stk = new Stack<String>();
		if (isCompleted == true) {
			stk.push("Completed");
		} else {
			stk.push("In Progress.");
		}
		stk.push(String.valueOf(price));
		stk.push(String.valueOf(quantity));
		stk.push(item.toString());
		stk.push(person.toString());
		stk.push(orderNumber.toString());
		return stk;
	}

	@Override
	public String toString() {
		return String.format("Order Number %s by %s%nOrder of %d of item number %s%nPrice: %.2f. Order Fulfilled? %b%n",
				orderNumber.toString(), person.toString(), quantity, item.toString(), price, isCompleted);
	}

	public Identity getKey() {
		return orderNumber;
	}

	static int getCounterState() {
		return counter;
	}

	static void setCounterState(int count) {
		counter = count;
	}

	@Override
	public String getName() {
		return Data.getUser(person).getName();
	}

}
