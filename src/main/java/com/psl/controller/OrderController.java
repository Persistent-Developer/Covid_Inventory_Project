
package com.psl.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.psl.entity.Inventory;
import com.psl.entity.Orders;
import com.psl.service.OrderService;
import com.psl.util.Response;

@RestController
public class OrderController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	public OrderService service;
	
	@PostMapping("/order")
	public ResponseEntity<Response<Orders>> placeOrder(@RequestBody Orders o) {
		LOGGER.info("Called : /order");
		Response<Orders> response = new Response<Orders>();
		response.setStatus(404);
		response.setstatusMessage("Unable to place order");
		
		Orders ord = new Orders();
		try {
			LOGGER.debug("In try block of placeOrder() function .. ");
			ord = service.placeOrder(o);
			if(ord!=null) {
				response.setResult(ord);
				response.setStatus(200);
				response.setstatusMessage("SUCCESS");
			}
		} catch (Exception e) {
			LOGGER.debug("In Exception of placeOrder() function .. ");
			response.setstatusMessage(e.getMessage());
			return new ResponseEntity<Response<Orders>>(response, HttpStatus.SERVICE_UNAVAILABLE);
		}
		
		return new ResponseEntity<Response<Orders>>(response, HttpStatus.OK);
		
	}
	
	@GetMapping("/orders")
	public ResponseEntity<Response<List<Orders>>> getAllOrders() {
		LOGGER.info("Called : /orders");
		Response<List<Orders>> response = new Response<List<Orders>>();
		response.setStatus(404);
		response.setstatusMessage("Unable to place order");
		
		List<Orders> ordList = null;
		try {
			LOGGER.debug("In try block of getAllOrders() function .. ");
			ordList = service.getAllOrders();
			if(ordList.size()<=0)
			{
				return new ResponseEntity<Response<List<Orders>>>(response, HttpStatus.NOT_FOUND);
			}
			response.setResult(ordList);
			response.setTotalElements(service.getAllOrders().size());
			response.setStatus(200);
			response.setstatusMessage("SUCCESS");
			return new ResponseEntity<Response<List<Orders>>>(response, HttpStatus.OK);
			
		} catch (Exception e) {
			LOGGER.debug("In Exception of getAllOrders() function .. ");
			response.setstatusMessage(e.getMessage());
			return new ResponseEntity<Response<List<Orders>>>(response, HttpStatus.NOT_FOUND);
		}	
		
		
	}
}
