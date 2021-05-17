package com.project.microservice.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.project.microservice.bean.CurrencyConversionBean;
import com.project.microservice.bean.LimitConfiguration;  

@RestController
public class LimitsConfigurationController {
	
	@Autowired  
	private CurrencyExchangeServiceProxy proxy;  
	
	@GetMapping("/limits")  
	public LimitConfiguration retriveLimitsFromConfigurations()  
	{  
	return new LimitConfiguration(1000, 1);  
	} 
	
	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}") //where {from} and {to} represents the column returns a bean back  
	public CurrencyConversionBean LimitsConfigurationController(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity)  
	{  
	//setting variables to currency exchange service  
	Map<String, String>uriVariables=new HashMap<>();  
	uriVariables.put("from", from);  
	uriVariables.put("to", to);  
	//calling the currency-exchange-service  
	//ResponseEntity<CurrencyConversionBean>responseEntity=new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversionBean.class, uriVariables);  
	//CurrencyConversionBean response=responseEntity.getBody(); 
	CurrencyConversionBean response = proxy.LimitsConfigurationController(from,to);
	System.out.println(response.toString());
	//creating a new response bean and getting the response back and taking it into Bean  
	return new CurrencyConversionBean(response.getId(), from,to,response.getConversionMultiple(), quantity,quantity.multiply(response.getConversionMultiple()),response.getPort());  
	} 
	 
	
}
