package com.psl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psl.dao.IRoleDAO;
import com.psl.entity.Role;

@Service("roleService")
public class RoleService {

	@Autowired
	private IRoleDAO dao;
	
	public Role addRole(Role role) throws Exception
	{
		return dao.save(role);
	}
	
	public Role getRole(int id) throws Exception
	{
		return dao.findById(id).get();
	}
	
}
