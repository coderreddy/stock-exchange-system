package com.kris.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class GetQuantityImpl implements GetQuantity{

	public ResponseWrapper getSumOfQuantityByUserAndRIC(String ric, String user) 
	{
		//Relates to the Entity ExecutedOrders table in the DB
		List<Order> executedOpenOrdersList = new ArrayList<Order>();
		ResponseWrapper response = new ResponseWrapper();
		
		Map<String,Double> quantityMap = new HashMap<String,Double>();
		
		for(Order orders : executedOpenOrdersList)
		{
			if(orders!= null && ric.equalsIgnoreCase(orders.getRIC()) && user.equalsIgnoreCase(orders.getUser()))
			{
				double sum = getSumOfExecutionQuantities(executedOpenOrdersList);
				if(!quantityMap.containsKey(ric+user))
				{
					quantityMap.put(ric+user, sum);
				}
			}
		}
		response.setAverageExeValues(quantityMap);
		return response;
	}
	
	/*
	 * Calculates the average execution price by all the executed orders for the provided RIC. 
	 */
	private double getSumOfExecutionQuantities(List<Order> executedOpenOrdersList)
	{
		double sum = 0;
		if(!executedOpenOrdersList.isEmpty())
		{
			
			int x = 0; 
			    do {
			        sum += executedOpenOrdersList.get(x).getQuantity();
			        x++;
			    } while (x < executedOpenOrdersList.size());
		}
		return sum;
	}

}
