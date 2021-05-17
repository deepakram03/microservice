package com.project.microservice.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.project.microservice.bean.CurrencyConversionBean;
@Component
@FeignClient(name="currency-exchange-service", url="localhost:8000")
//@FeignClient(name="currency-exchange-service")
//@RibbonClient(name="currency-exchange-service") 
public interface CurrencyExchangeServiceProxy {
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}") 
	CurrencyConversionBean LimitsConfigurationController(@PathVariable String from, @PathVariable String to);
}
