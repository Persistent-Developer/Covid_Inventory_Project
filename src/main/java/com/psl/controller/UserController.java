package com.psl.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.psl.entity.User;
import com.psl.service.UserService;


@RestController
public class UserController {

	@Autowired
	private UserService service;

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

//---------------------------------------------------------------------------
	@GetMapping("/user/{id}")
	public User getUser(@PathVariable int id)
	{
		User usr = new User();
		try {
			LOGGER.debug("In getUser controller using id...");
			usr = service.getUser(id);
		}catch (Exception e) {
			LOGGER.debug("In Exception of getUser controller using id...");
			e.printStackTrace();
		}
		return usr;
	}

	@GetMapping("/users")
	public List<User> getAllUsers()
	{
		List<User> lstUser=null;
		try {
			LOGGER.debug("In get all User controller...");
			lstUser = service.getAllUsers();
		}catch (Exception e) {
			LOGGER.debug("In Exception of get all User controller...");
			e.printStackTrace();
		}
		return lstUser;
	}

	@GetMapping("/user/customers")
	public List<User> getAllCustomers()
	{
		List<User> lstUser=null;
		try {
			LOGGER.debug("In get all Customers controller...");
			lstUser = service.getAllCustomers();
		}catch (Exception e) {
			LOGGER.debug("In Exception of get all Customers controller...");
			e.printStackTrace();
		}
		return lstUser;
	}

//---------------------------------------------------------------------------
	@PostMapping("/user/register")
	public User addUser(@RequestBody User user)
	{
		LOGGER.info("Called : /user/register      to add user");
		User user1 = new User();
		try {
			LOGGER.debug("In try block of add User controller...");
			user1 = service.addUser(user1);
		} catch (Exception e) {
			LOGGER.debug("In Exception of add User controller...");
			e.printStackTrace();
		}
		return user1;
	}

//---------------------------------------------------------------------------

	@DeleteMapping("/users/{id}")
	public String deleteUserById(@PathVariable int id)
	{	String msg=null;
		try {
			LOGGER.debug("In delete user by Id controller...");
			msg = service.deleteUserById(id);
		} catch (Exception e) {
			LOGGER.debug("In Exception of delete user by Id controller...");
			e.printStackTrace();
		}
		
		return msg;
	}

//---------------------------------------------------------------------------

	@PutMapping("/users/{id}")
	public String updateUserById(@RequestBody User user,@PathVariable int id)
	{
		LOGGER.info("Called : /user/{id}      to update user by id");
		String msg=null;
		try {
			LOGGER.debug("In try block of update user by Id controller...");
			msg = service.updateUserById(user,id);
		} catch (Exception e) {
			LOGGER.debug("In Exception of update user by Id controller...");
			e.printStackTrace();
		}
		
		return msg;
	}


//---------------------------------------------------------------------------


	
	  @PostMapping("/user/uploadFile") 
	  public List<User> uploadMultipartFile(@RequestParam("uploadfile") MultipartFile file) {
		  LOGGER.info("Called : /user/uploadFile      to add multiple users"); 
		  try {
			  LOGGER.debug("In try block of update user by Excel file controller...");
			  service.store(file);
		  
		  } catch (Exception e) { 
			  LOGGER.debug("In Exception of update user by Excel file controller...");
			  e.printStackTrace(); 
		  } 
		  List<User> uList = service.getAllUser();
		  
	  //return "file updated succesfully"; 
		  //return service.getAllUser(); 
		  return uList; 
	}
	 

	@PostMapping("/user/change/emailid/{id}")
	public String changeEmail(@RequestBody ObjectNode objectNode,@PathVariable int id)
	{
		LOGGER.info("Called : /user/change/emailid/{id}      to update email id of user");
		String oldEmail = objectNode.get("Old Email").asText();
		String newEmail = objectNode.get("New Email").asText();
		
		String msg=null;
		try {
			LOGGER.debug("In try block of change email by Id controller...");
			msg=service.changeEmailId(oldEmail,newEmail, id);
		} catch (Exception e) {
			LOGGER.debug("In Exception of change email by Id controller...");
			e.printStackTrace();
		}
		return msg;
	}

}
