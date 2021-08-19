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

@RestController
public class StoreController {

	private static final Logger LOGGER = LoggerFactory.getLogger(StoreController.class);
	
	@Autowired
	private StoreService service;

	// Add store details
	@PostMapping("/store")
	public ResponseEntity<Store> addStore(@RequestBody Store store) {
		LOGGER.info("Called: /store  ");
		Store store1 = new Store();
		try {
			store1 = service.addStore(store1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<>(store1,HttpStatus.OK);
	}

	// Retrieve Store details based on store id
	@GetMapping("/store/{id}")
	public ResponseEntity<Store> getStore(@PathVariable int id) 
	{ 
		Store st = null;

		try {
			LOGGER.debug("In getStore controller...");
			st = service.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (st == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		return ResponseEntity.of(Optional.of(st));
	}

	// Update name, is_active, no_of_slots, slot_duration from store
	@GetMapping("/store")
	public ResponseEntity<List<Store>> getStore() {
		List<Store> list = null;
		try {
			LOGGER.debug("In getAllStore controller...");
			list = service.getAllStore();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (list.size() <= 0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		return ResponseEntity.of(Optional.of(list));
	}

	// Update Store details
	@PutMapping("/store")
	public ResponseEntity<Store> updateStore(@RequestBody Store s) {
		LOGGER.info("Called : /store     to update");
		Store store = new Store();
		try {
			store=service.addStore(s);
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return new ResponseEntity<>(store,HttpStatus.OK);
	}
}
