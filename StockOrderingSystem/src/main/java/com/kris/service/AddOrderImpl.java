package com.kris.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

/*
 * Add the new Sell/Buy order, compares the new order against the existing Sell/Buy open orders and executes matching orders. 
 */
@Component
public class AddOrderImpl implements AddOrder{

	/*
	 * @see com.kris.service.AddOrder#addOrder(com.kris.service.Order)
	 * Add the order and compares against the existing orders and executes ther matching orders
	 */
	public void addOrder(Order order) {
		
		//Relates to the Entity SellOpenOrders table in the DB
		List<Order> sellOrderList = new LinkedList<Order>();
		//Relates to the Entity BuyOpenOrders table in the DB
		List<Order> buyOrderList = new LinkedList<Order>();
		//Relates to the Entity ExecutedOrders table in the DB
		List<Order> executedOpenOrdersList = new ArrayList<Order>();
		
		if(order != null)
		{
			String direction = order.getDirection();
			//if direction is SELL, then compare the order against open Buy order list
			if("SELL".equalsIgnoreCase(direction))
			{
				//add the new order to the open SellOrderList
				sellOrderList.add(order);
				if(!buyOrderList.isEmpty())
				{
					Iterator<Order> it = buyOrderList.iterator();
					while(it.hasNext())
					{
						executeSellOrder(order, sellOrderList, buyOrderList, executedOpenOrdersList, it);
					}
				}
			}
			//if direction is BUY, then compare the order against open Sell order list
			else if("BUY".equalsIgnoreCase(direction))
			{
				//add the new order to open BuyOrderList
				buyOrderList.add(order);
				if(!sellOrderList.isEmpty())
				{
					Iterator<Order> it = sellOrderList.iterator();
					while(it.hasNext())
					{
						executeBuyOrder(order, sellOrderList, buyOrderList, executedOpenOrdersList, it);
						
					}
				}
			}
		}
		
	}


	/*
	 * Compares the new order with the existing open Sell orders and executes. 
	 */
	private void executeBuyOrder(Order order, List<Order> sellOrderList, List<Order> buyOrderList,
			List<Order> executedOpenOrdersList, Iterator<Order> it) 
	{
		//get the highest Buy open order with highest price
		Order highestbuyOrder = getBuyOrderWithHighestPrice(buyOrderList);
		Order sellOrder = it.next();
		if(sellOrder != null)
		{
		String RIC = order.getRIC();
		int quantity = order.getQuantity();
		double buyPrice = order.getPrice();
		//get the lowest sell open order price
		double lowestSellPrice = getSellOrderWithLowestPrice(sellOrderList).getPrice();
			if((RIC != null || RIC != "") && quantity != 0 && buyPrice != 0)
			{
				if(RIC.equalsIgnoreCase(sellOrder.getRIC()) && quantity == sellOrder.getQuantity() 
						&& buyPrice >= lowestSellPrice)
				{
					//verify if the new Buy order is the order with highest price 
					if(highestbuyOrder.equals(order))
					{
						executedOpenOrdersList.add(order);
						it.remove();
						buyOrderList.remove(order);
					}
				}
			}
		}
	}


	/*
	 * Compares the new order with the existing open Buy orders and executes. 
	 */
	private void executeSellOrder(Order order, List<Order> sellOrderList, List<Order> buyOrderList,
			List<Order> executedOpenOrdersList, Iterator<Order> it) 
	{
		//get the lowest sell order with lowest price
		Order lowestSellOrder = getSellOrderWithLowestPrice(sellOrderList);
		Order buyOrder = it.next();
		if(buyOrder != null)
		{
		String RIC = order.getRIC();
		int quantity = order.getQuantity();
		double sellprice = order.getPrice();
		//get the lowest sell buy price
		double highestBuyPrice = getBuyOrderWithHighestPrice(buyOrderList).getPrice();
			if((RIC != null || RIC != "") && quantity != 0 && sellprice != 0)
			{
				if(RIC.equalsIgnoreCase(buyOrder.getRIC()) && quantity == buyOrder.getQuantity() 
						&& sellprice <= highestBuyPrice)
				{
					//verify if the new Sell order is the order with lowest price
					if(lowestSellOrder.equals(order))
					{
						executedOpenOrdersList.add(order);
						it.remove();
						sellOrderList.remove(order);
					}
				}
			}
		}
	}


	/*
	 * Returns the highest price from the buy order list
	 */
	private Order getBuyOrderWithHighestPrice(List<Order> buyOrderList)
	{
		return Collections.max(buyOrderList,new PriceOrderComparator());
	}
	
	/*
	 * Returns the lowest price from the sell order list
	 */
	private Order getSellOrderWithLowestPrice(List<Order> sellOrderList)
	{
		Order order = null;
		if(!sellOrderList.isEmpty())
		{
		order = Collections.min(sellOrderList,new PriceOrderComparator());
		}
		return order;
	}

}
