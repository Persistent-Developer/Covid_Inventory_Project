package com.psl.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
import com.psl.util.Response;


@RestController
public class UserController {

	@Autowired
	private UserService service;

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

//---------------------------------------------------------------------------
	@GetMapping("/users/{id}")
	public ResponseEntity<Response<User>> getUser(@PathVariable int id)
	{
		Response<User> response = new Response<User>();
		User usr = new User();
		
		try {
			LOGGER.debug("In getUser controller using id...");
			
			usr = service.getUser(id);
			if(usr == null)
			{  
				return new ResponseEntity<Response<User>>(response, HttpStatus.NOT_FOUND);
			}
			response.setStatus(200);
			response.setstatusMessage("SUCCESS");
			response.setResult(usr);
			return new ResponseEntity<Response<User>>(response, HttpStatus.OK);
		
		}catch (Exception e) {
			response.setstatusMessage(e.getMessage());
			response.setStatus(500);
			return new ResponseEntity<Response<User>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
	}
//---------------------------------------------------------------------------
	
	@GetMapping("/user/get/{phNumber}")
	public ResponseEntity<Response<User>> getUserByPhNumber(@PathVariable String phNumber)
	{
		Response<User> response = new Response<User>();
		User usr = new User();
		
		try {
			LOGGER.debug("In getUser by using phone number api...");
			usr = service.findByPhNumber(phNumber);
			if(usr == null)
			{  
				return new ResponseEntity<Response<User>>(response, HttpStatus.NOT_FOUND);
			}
			response.setStatus(200);
			response.setstatusMessage("SUCCESS");
			response.setResult(usr);
			return new ResponseEntity<Response<User>>(response, HttpStatus.OK);
		}
		
		catch (Exception e) {
			response.setstatusMessage(e.getMessage());
			response.setStatus(500);
			return new ResponseEntity<Response<User>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
}
//---------------------------------------------------------------------------	

	@GetMapping("/users/get/{email}")
	public ResponseEntity<Response<User>> getUserByEmail(@PathVariable String email)
	{
		Response<User> response = new Response<User>();
		User usr = null;
		
		try {
			LOGGER.debug("In getUser by using Email api...");
			usr = service.findByEmail(email);
			if(usr == null)
			{  
				return new ResponseEntity<Response<User>>(response, HttpStatus.NOT_FOUND);
			}
			response.setStatus(200);
			response.setstatusMessage("SUCCESS");
			response.setResult(usr);
			
			return new ResponseEntity<Response<User>>(response, HttpStatus.OK);
		
		 }
		catch (Exception e) {
		    response.setstatusMessage(e.getMessage());
			response.setStatus(500);
			return new ResponseEntity<Response<User>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

	@GetMapping("/users")
	public ResponseEntity<Response<List<User>>> getAllUsers()
	{
	Response<List<User>> response = new Response<List<User>>();
	List<User> lstUser=null;
	try {
		LOGGER.debug("In get all User controller");
		lstUser = service.getAllUsers();
		
		if(lstUser.size() <= 0)
		{  
			return new ResponseEntity<Response<List<User>>>(response, HttpStatus.NOT_FOUND);
		}
		
		response.setStatus(200);
		response.setstatusMessage("SUCCESS");
		response.setResult(lstUser);
		response.setTotalElements(lstUser.size());
		return new ResponseEntity<Response<List<User>>>(response, HttpStatus.OK);
	}
	catch (Exception e) {
		response.setstatusMessage(e.getMessage());
		response.setStatus(500);
		return new ResponseEntity<Response<List<User>>>(response, HttpStatus.INTERNAL_SERVER_ERROR );
	}
}

	@GetMapping("/user/customers")
	public ResponseEntity<Response<List<User>>> getAllCustomers()
	{
		Response<List<User>> response = new Response<List<User>>();
		List<User> lstUser=null;
		try {
			LOGGER.debug("In get all Customers controller...");
			lstUser = service.getAllCustomers();
			
			if(lstUser.size() <= 0)
			{  
				return new ResponseEntity<Response<List<User>>>(response, HttpStatus.NOT_FOUND);
			}
			
			response.setStatus(200);
			response.setstatusMessage("SUCCESS");
			response.setResult(lstUser);
			response.setTotalElements(lstUser.size());
			return new ResponseEntity<Response<List<User>>>(response, HttpStatus.OK);
		}
		catch (Exception e) {
			response.setstatusMessage(e.getMessage());
			response.setStatus(500);
			return new ResponseEntity<Response<List<User>>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}

	@PostMapping("/user/register")
	public void addUser(@RequestBody User user)
	{
		LOGGER.info("Called : /user/register      to add user");
		try {
			service.addUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//---------------------------------------------------------------------------

	@DeleteMapping("/users/{id}")
	public ResponseEntity<Response<String>> deleteUserById(@PathVariable int id)
	{
		Response<String> response = new Response<String>();
		try {
			
			service.deleteUserById(id);
			
			response.setStatus(200);
			response.setstatusMessage("SUCCESSFUL DELETION");
			return new ResponseEntity<Response<String>>(response, HttpStatus.OK);

	    }
		
	    catch (Exception e) {
	    	response.setstatusMessage("Deletion unsuccessful as user id "+ id + " is not found");
	    	response.setStatus(500);
			return new ResponseEntity<Response<String>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}	
		
	}

//---------------------------------------------------------------------------

	@PatchMapping("/users/{id}")
	public String updateUserById(@RequestBody User user,@PathVariable int id)
	{
		LOGGER.info("Called : /user/{id}      to update user by id");
		String msg=null;
		try {
			msg = service.updateUserById(user,id);
		} catch (Exception e) {
			return "Updation unsuccessful as user id "+ id + " is not found" ;
		}
		return msg;

	}


//---------------------------------------------------------------------------


	@PostMapping("/user/uploadFile")
    public List<User> uploadMultipartFile(@RequestParam("uploadfile") MultipartFile file) {
		LOGGER.info("Called : /user/uploadFile      to add multiple users");
		try {
			service.store(file);

		} catch (Exception e) {
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
			msg=service.changeEmailId(oldEmail,newEmail, id);
		} catch (Exception e) {
			return "Email Updation unsuccessful as user id "+ id + " is not found";
		}
		return msg;
	}

	
	@PostMapping("/user/change/password/{id}")
	public String changePassword(@RequestBody ObjectNode objectNode,@PathVariable int id) 
	{
		String str="";
		String oldPassword = objectNode.get("Old Password").asText();
		String newPassword = objectNode.get("New Password").asText();
		try {
			 str = service.changePassword(oldPassword,newPassword, id);
		
		} catch (Exception e) {
		return "Password Updation unsuccessful as user id "+ id + " is not found";		
	  
	  }
	  return str;
	}
}
