package model;

import java.io.Serializable;

public class Item implements Serializable{
	
	private static final long serialVersionUID = -3775776854105022190L;
	private String name;
	private double price;
	private long id;
	private static long idCount = 0;
	private Source donator;

}
