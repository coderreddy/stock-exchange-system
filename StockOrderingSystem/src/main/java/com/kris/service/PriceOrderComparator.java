package com.kris.service;

import java.util.Comparator;

public class PriceOrderComparator implements Comparator<Order> {

	public int compare(Order a, Order b) {
		if(a.getPrice() > b.getPrice())
		{
			return 1;
		}
		else if(a.getPrice() < b.getPrice())
		{
			return -1;
		}
		else if(a.getPrice() == b.getPrice())
		{
			return 0;
		}
		return 0;
	}

}
