package com.kris.service;

import java.util.Map;

public class OpenInterest {
	
	private Map<Double, Integer> openOrders;
	
	public Map<Double, Integer> getOpenOrders() {
		return openOrders;
	}

	public void setOpenOrders(Map<Double, Integer> openOrders) {
		this.openOrders = openOrders;
	}	

}
