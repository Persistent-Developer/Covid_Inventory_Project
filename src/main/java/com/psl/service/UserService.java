package com.psl.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.psl.dao.IUserDAO;
import com.psl.entity.Role;
import com.psl.entity.User;
import com.psl.util.ExcelUtils;


@Service("UserService")
public class UserService {

	@Autowired
	private IUserDAO dao;
	
	public void addUser(User user)
	{
		Role role = user.getRole();
		if(role.getRoleId()== 1) {
			role.setRoleName("Admin");
		}
		else if(role.getRoleId()== 2) {
			role.setRoleName("Aggregator");
		}
		else if(role.getRoleId()== 3) {
			role.setRoleName("Customer");
		}

		dao.save(user);
	}

//---------------------------------------------------------------------------	
	public User getUser(int id)
	{
		User user = null ;
		try {
		user = dao.findById(id).get();
		}
		catch (Exception e) {
		e.printStackTrace();
		}
		
		return user;
	}
    
	public List<User> getAllUsers()
	{
		List<User> users = new ArrayList<User>();
		
		for(User user : dao.findAll())
			users.add(user);
		
		return users;
	}
	
	public List<User> getAllCustomers()
	{
		List<User> users = new ArrayList<User>();
		
		for(User user : dao.findAll()) {
			
			if((user.getRole()).getRoleId() == 3)
					users.add(user);
		}
		
		return users;
	}
//---------------------------------------------------------------------------	
	
	public void deleteUserById(int id) throws Exception
	{
		User user = dao.findById(id).get();
		
		if(user.getUserId() == id)
			dao.deleteById(id);
	}

//---------------------------------------------------------------------------	

	
	public List<User> getAllUser() {
		return (List<User>) dao.findAll();
	}
	
	public void store(MultipartFile file) {
		try {
			
			ExcelUtils util = new ExcelUtils(dao);
			util.parseUserExcelFile(file.getInputStream());
			
			//List<User> lstUsers = ExcelUtils.parseUserExcelFile(file.getInputStream());
			
    		//dao.saveAll(lstUsers);
        } catch (IOException e) {
        	throw new RuntimeException("FAIL! -> message = " + e.getMessage());
        }
	}
	
	public String updateUserById(User user, int id) throws Exception  {
			User user1 = dao.findById(id).get();
			
			if(user1.getUserId() == id)
				dao.save(user);
			
	        return "updation Successful";	
	}
	
	public String changeEmailId(String oldEmail, String newEmail, int id) throws Exception  {
	
		User user = dao.findById(id).get();
		
		if(user.getUserId() == id && user.getEmail().equals(oldEmail))
		{
			user.setEmail(newEmail);
			dao.save(user);
		   //dao.changeEmailId(newEmail, id);
		   return "New Email \""+ newEmail + "\" is set for user with id " + id;
		}
		else
		{
			return "check your old Email again";
		}
	
	
	}
	
	public String changePassword(String oldPassword, String newPassword, int id) throws Exception {
	
	    User user = dao.findById(id).get();
		
		if(user.getUserId() == id && user.getPassword().equals(oldPassword))
		{
			user.setPassword(newPassword);
			dao.save(user);
		   return "New Password is set for user with id " + id;
		}
		else
		{
			return "check your old Password again";
		}
		
	}
	
	public User findByPhNumber(String phNumber)  {
		 User user = null; 
		
			user = dao.findByPhNumber(phNumber);
		
		
		return user;

	}

	public User findByEmail(String email) {
		User user = null; 
		
		user = dao.findByEmail(email);
		
		return user; 
	}
		
}
