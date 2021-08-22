package com.psl.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.psl.entity.Role;
import com.psl.service.RoleService;

@RestController
public class RoleController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RoleController.class);
	
	@Autowired
	private RoleService service;
	
	@GetMapping("/role/{id}")
	public Role getRole(@PathVariable int id)
	{
		Role role = new Role();
		try {
			LOGGER.debug("In getRole() function...");
			role = service.getRole(id);
		} catch (Exception e) {
			LOGGER.debug("In Exception of getRole() function...");
		}
		
		return role;
	}
	
	@PostMapping("/role")
	public Role addRole(@RequestBody Role role)
	{
		LOGGER.info("Called:  /role ");
		Role role1 = new Role();
		try {
			role1 = service.addRole(role1);
		} catch (Exception e) {
			LOGGER.debug("In Exception of addRole() function...");
		}
		
		return role1;
	}
}
