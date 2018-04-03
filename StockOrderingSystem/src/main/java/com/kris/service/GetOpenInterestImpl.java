package com.kris.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class GetOpenInterestImpl implements GetOpenInterest{

	public OpenInterest getOpenInterest(String ric, String direction) 
	{
		//Relates to the Entity SellOpenOrders table in the DB
		List<Order> sellOrderList = new LinkedList<Order>();
		//Relates to the Entity BuyOpenOrders table in the DB
		List<Order> buyOrderList = new LinkedList<Order>();
		
		Map<Double, Integer> openOrders = new HashMap<Double,Integer>();
		
		OpenInterest openInterest = new OpenInterest();
		
		if(ric != null && direction != null)
		{
			if("SELL".equalsIgnoreCase(direction))
			{
				for(Order sellOrder : sellOrderList)
				{
					double price  = sellOrder.getPrice();
					if(ric.equalsIgnoreCase(sellOrder.getRIC()))
					{
						if(!openOrders.containsKey(price))
						{
							openOrders.put(price, 1);
						}
						else
						{
							openOrders.put(price, openOrders.get(price)+1);
						}
					}
				}
				openInterest.setOpenOrders(openOrders);
			}
			else if("BUY".equalsIgnoreCase(direction))
			{
				for(Order buyOrder : buyOrderList)
				{
					double price  = buyOrder.getPrice();
					if(ric.equalsIgnoreCase(buyOrder.getRIC()))
					{
						if(!openOrders.containsKey(price))
						{
							openOrders.put(price,1);
						}
						else
						{
							openOrders.put(price, openOrders.get(price)+1);
						}
					}
				}
				openInterest.setOpenOrders(openOrders);
			}
		}
		
		return openInterest;
	}

}
