package com.psl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.psl.entity.Orders;
import com.psl.service.OrderService;

@RestController
public class OrderController {
	
	@Autowired
	public OrderService service;
	
	@PostMapping("/order")
	public void placeOrder(@RequestBody Orders o) {
		service.placeOrder(o);
	}
}
