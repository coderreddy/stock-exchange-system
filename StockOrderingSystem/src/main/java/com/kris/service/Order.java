package com.kris.service;

public class Order {
	
	private String direction;
	
	private String ric;
	
	private int quantity;
	
	private volatile double price;
	
	private String user;

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getRIC() {
		return ric;
	}

	public void setRIC(String rIC) {
		ric = rIC;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return String.format("{\"DIRECTION : %s\", \"RIC : %s\", \"QUANTITY : %s\", \"PRICE : %s\", \"USER : %s\"}", direction,ric,quantity,price);
		
	}
	
	

}
