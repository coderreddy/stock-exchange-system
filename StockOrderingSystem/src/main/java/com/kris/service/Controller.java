package com.kris.service;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

	@Resource
	private AddOrderImpl addOrderImpl;
	
	@Resource
	private GetOpenInterestImpl getOpenInterestImpl;
	
	@Resource
	private GetAverageExecutionPriceImpl getAverageExecutionPriceImpl;
	
	@Resource
	private GetQuantityImpl getQuantityImpl;

	@RequestMapping(value = "/addOrder",method=RequestMethod.POST)
	public String addOrder(@RequestBody(required = true) Order order) 
	{
		try 
		{
			addOrderImpl.addOrder(order);
		} 
		catch (Exception e) 
		{
			return "Failed adding the order";
		}
		return "Added the order sucessfully";
	}
	
	@RequestMapping(value="/getOpenInterest",method=RequestMethod.GET)
	public OpenInterest getOpenInterest(String ric, String direction)
	{
		OpenInterest openInterest = getOpenInterestImpl.getOpenInterest(ric, direction);
		return openInterest;
	}
	
	@RequestMapping(value="/getAvgExePriceByRIC",method=RequestMethod.GET)
	public ResponseWrapper getAvgExePriceByRIC(String ric)
	{
		ResponseWrapper response = getAverageExecutionPriceImpl.getAvgExePriceByRIC(ric);
		return response;
	}
	
	@RequestMapping(value="/getSumOfQuantityByUserAndRIC",method=RequestMethod.GET)
	public ResponseWrapper getSumOfQuantityByUserAndRIC(String ric,String user)
	{
		ResponseWrapper response = getQuantityImpl.getSumOfQuantityByUserAndRIC(ric, user);
		return response;
	}

}
