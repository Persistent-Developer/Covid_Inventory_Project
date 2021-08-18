package com.psl.controller;

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
	
	@Autowired
	private RoleService service;
	
	@GetMapping("/role/{id}")
	public Role getRole(@PathVariable int id)
	{
		return service.getRole(id);
	}
	
	@PostMapping("/role")
	public void addRole(@RequestBody Role role)
	{
		service.addRole(role);
	}
}
