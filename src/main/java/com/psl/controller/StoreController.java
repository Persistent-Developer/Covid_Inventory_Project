package com.psl.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.psl.entity.Inventory;
import com.psl.entity.Orders;

import com.psl.entity.Store;
import com.psl.service.StoreService;
import com.psl.util.Response;

@RestController
public class StoreController {

	private static final Logger LOGGER = LoggerFactory.getLogger(StoreController.class);
	
	@Autowired
	private StoreService service;

	// Add store details
	@PostMapping("/store")
	public ResponseEntity<Response<Store>> addStore(@RequestBody Store store) {
		LOGGER.info("Called: /store  ");
		Response<Store> response = new Response<Store>();
		response.setStatus(404);
		response.setstatusMessage("Unable to add store");
		Store store1 = new Store();
		try {
			LOGGER.debug("In try block of addStore() function ..");
			store1 = service.addStore(store);
			if(store1!=null) {
				response.setResult(store1);
				response.setStatus(200);
				response.setstatusMessage("SUCCESS");
			}
		} catch (Exception e) {
			LOGGER.debug("In Exception of addStore() function ..");
			response.setstatusMessage(e.getMessage());
			return new ResponseEntity<Response<Store>>(response, HttpStatus.SERVICE_UNAVAILABLE);
		}
		
		return new ResponseEntity<Response<Store>>(response, HttpStatus.OK);
	}

	// Retrieve Store details based on store id
	@GetMapping("/store/{id}")
	public ResponseEntity<Response<Store>> getStore(@PathVariable int id) 
	{ 
		LOGGER.info("Called : /store/{id}");
		Response<Store> response = new Response<Store>();
		response.setStatus(404);
		response.setstatusMessage("Unable to get Store");
		Store st = null;

		try {
			LOGGER.debug("In getStore controller...");
			st = service.findById(id);
			if(st!=null) {
				response.setResult(st);
				response.setTotalElements(service.getAllStore().size());
				response.setStatus(200);
				response.setstatusMessage("SUCCESS");
			}
		} catch (Exception e) {
			LOGGER.debug("In Exception of getStore() function ..");
			response.setstatusMessage(e.getMessage());
			return new ResponseEntity<Response<Store>>(response, HttpStatus.SERVICE_UNAVAILABLE);
		}
//		if (st == null) {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//		}

		return new ResponseEntity<Response<Store>>(response, HttpStatus.OK);
	}

	// get all stores
	@GetMapping("/store")
	public ResponseEntity<Response<List<Store>>> getStore() {
		LOGGER.info("Called : /store");
		Response<List<Store>> response = new Response<List<Store>>();
		response.setStatus(404);
		response.setstatusMessage("Unable to get Stores");
		
		List<Store> storeList = null;
		try {
			LOGGER.debug("In getAllStore controller...");
			storeList = service.getAllStore();
			if(storeList.size()<=0)
			{
				return new ResponseEntity<Response<List<Store>>>(response, HttpStatus.NOT_FOUND);
			}
			response.setResult(storeList);
			response.setTotalElements(service.getAllStore().size());
			response.setStatus(200);
			response.setstatusMessage("SUCCESS");
			return new ResponseEntity<Response<List<Store>>>(response, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.debug("In Exception of getAllStore() function ..");
			response.setstatusMessage(e.getMessage());
			return new ResponseEntity<Response<List<Store>>>(response, HttpStatus.NOT_FOUND);
		}

		
	}

	// Update Store details
	@PutMapping("/store")
	public ResponseEntity<Response<Store>> updateStore(@RequestBody Store s) {
		LOGGER.info("Called : /store     to update");
		Response<Store> response = new Response<Store>();
		response.setStatus(404);
		response.setstatusMessage("Unable to update store data.");
		Store store = new Store();
		try {
			LOGGER.debug("In try block of updateStore() function ..");
			store=service.addStore(s);
			if(store!=null) {
				response.setResult(store);
				response.setStatus(200);
				response.setstatusMessage("SUCCESS");
			}
			
		} catch (Exception e) {
			LOGGER.debug("In Exception of updateStore() function ..");
			response.setstatusMessage(e.getMessage());
			return new ResponseEntity<Response<Store>>(response, HttpStatus.SERVICE_UNAVAILABLE);
		}
		return new ResponseEntity<Response<Store>>(response, HttpStatus.OK);
	}
}
