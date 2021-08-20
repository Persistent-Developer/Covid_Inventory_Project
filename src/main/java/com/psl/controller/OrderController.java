package com.psl.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.psl.entity.Orders;
import com.psl.service.OrderService;

@RestController
public class OrderController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	public OrderService service;
	
	@PostMapping("/order")
	public Orders placeOrder(@RequestBody Orders o) {
		LOGGER.info("Called : /order");
		
		Orders ord = new Orders();
		try {
			ord = service.placeOrder(o);
		} catch (Exception e) {
			
		}
		
		return ord;
		
	}
}
