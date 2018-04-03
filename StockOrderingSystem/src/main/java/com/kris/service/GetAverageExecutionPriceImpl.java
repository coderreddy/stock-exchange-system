package com.kris.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class GetAverageExecutionPriceImpl implements GetAverageExecutionPrice{

	public ResponseWrapper getAvgExePriceByRIC(String ric) 
	{
		//Relates to the Entity ExecutedOrders table in the DB
		List<Order> executedOpenOrdersList = new ArrayList<Order>();
		Map<String,Double> averageExeValues = new HashMap<String,Double>();
		ResponseWrapper response = new ResponseWrapper();
		
		for(Order orders : executedOpenOrdersList)
		{
			if(ric.equalsIgnoreCase(orders.getRIC()))
			{
				double averageExecutionPrice = getAverageExecutionPrice(executedOpenOrdersList);
				if(!averageExeValues.containsKey(ric))
				{
					averageExeValues.put(ric, averageExecutionPrice);
				}
			}
		}
		 response.setAverageExeValues(averageExeValues);
		 return response;
	}
	
	/*
	 * Calculates the average execution price by all the executed orders for the provided RIC. 
	 */
	private double getAverageExecutionPrice(List<Order> executedOpenOrdersList)
	{
		double average = 0;
		if(!executedOpenOrdersList.isEmpty())
		{
			double sum = 0;
			int x = 0; 
			    do {
			        sum += executedOpenOrdersList.get(x).getPrice();
			        x++;
			    } while (x < executedOpenOrdersList.size());
		   average = sum / x;
		}
		return average;
	}

}
