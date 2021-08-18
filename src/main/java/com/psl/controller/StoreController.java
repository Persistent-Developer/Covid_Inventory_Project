package com.psl.controller;

import java.util.List;
import java.util.Optional;

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

	@Autowired
	private StoreService service;

	// Add store details
	@PostMapping("/store")
	public ResponseEntity<Void> addStore(@RequestBody Store store) {
		try {
			service.addStore(store);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

//	// Retrieve Store details based on store id
//	@GetMapping("/store/{id}")
//	public ResponseEntity<Store> getStore(@PathVariable int id) 
//	{ 
//		Store st = null;
//
//		try {
//			st = service.getStore(id);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		if (st == null) {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//		}
//
//		return ResponseEntity.of(Optional.of(st));
//	}
//
//	// Update name, is_active, no_of_slots, slot_duration from store
//	@GetMapping("/store")
//	public ResponseEntity<List<Store>> getStore() {
//		List<Store> list = null;
//		try {
//			list = service.getAllStore();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		if (list.size() <= 0) {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//		}
//
//		return ResponseEntity.of(Optional.of(list));
//	}
//
//	// Update Store details
//	@PutMapping("/store")
//	public ResponseEntity<Void> updateStore(@RequestBody Store s) {
//		try {
//			service.addStore(s);
//			return ResponseEntity.status(HttpStatus.CREATED).build();
//		} catch (Exception e) {
//			e.printStackTrace();
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//		}
//	}
}
