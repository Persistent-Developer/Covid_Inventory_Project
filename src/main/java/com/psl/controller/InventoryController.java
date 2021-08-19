package com.psl.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.psl.entity.Inventory;
import com.psl.entity.Store;
import com.psl.service.InventoryService;
import com.psl.service.StoreService;
import com.psl.util.Response;




@RestController
public class InventoryController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(InventoryController.class);
	
	@Autowired
	private InventoryService inventoryService;
	
	@Autowired
	private StoreService storeService;
	
	//Post single product into the inventory
	@PostMapping("/inventory")
	public ResponseEntity<Response<Inventory>> addProducts(@RequestBody Inventory inventory) throws Exception {
		Response<Inventory> response = new Response<Inventory>();
		response.setStatus(404);
		response.setstatusMessage("Unable to add item to inventory");
		Inventory item = null;
		Store store = storeService.findById(inventory.getStore().getId());
		if (inventory.getStore() == null)
			inventory.setStore(storeService.findById(1));
		try {
			item = inventoryService.addProducts(inventory);
			if (item != null) {
				response.setResult(item);
				response.setStatus(200);
				response.setstatusMessage("SUCCESS");
			}
		}
		catch (Exception e) {
				response.setstatusMessage(e.getMessage());
				return new ResponseEntity<Response<Inventory>>(response, HttpStatus.SERVICE_UNAVAILABLE);
			}
		
		return new ResponseEntity<Response<Inventory>>(response, HttpStatus.OK);	
		}

	
	//post products using excel file
	@PutMapping("/inventory/uploadFile")
    public ResponseEntity<?> uploadMultipartFile(@RequestParam("uploadfile") MultipartFile file/*, Model model*/) {
		LOGGER.info("Called : /inventory/uploadFile");
		
		List<Inventory> prodList = new ArrayList<>();
		try {
			LOGGER.info("Inserting inventory");
			inventoryService.store(file);
			
			prodList = inventoryService.getAllProducts();
			
		} catch (Exception e) {
			e.printStackTrace();
			
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}   
		
		return new ResponseEntity<>(prodList,HttpStatus.OK);
    }
	
	
	//Update product in inventory
	@PutMapping("/inventory/{id}")
	public ResponseEntity<Response<Inventory>> updateProducts(@PathVariable int id,@RequestBody Inventory inventory)
	{
		LOGGER.info("Called : /inventory           to update product");
		Response<Inventory> response = new Response<Inventory>();
		response.setStatus(404);
		response.setstatusMessage("Unable to update inventory data.");
		Inventory item = null;
		try {
			item=inventoryService.updateProducts(id,inventory);
			if (item != null) {
				response.setResult(item);
				response.setStatus(200);
				response.setstatusMessage("SUCCESS");
			}
		} catch (Exception e) {
			response.setstatusMessage(e.getMessage());
			return new ResponseEntity<Response<Inventory>>(response, HttpStatus.SERVICE_UNAVAILABLE);
		}
		return new ResponseEntity<Response<Inventory>>(response, HttpStatus.OK);
	}
	
	
	//Get all products
	@GetMapping("/inventory")
	public ResponseEntity<Response<List<Inventory>>> getAllProducts()
	{
		List<Inventory> list = null;
		Response<List<Inventory>> response = new Response<List<Inventory>>();
		response.setStatus(404);
		response.setstatusMessage("Data Not Found");
		
		try {
			LOGGER.debug("In getAllProducts controller...");
			list=inventoryService.getAllProducts();
			
			if(list.size()<=0)
			{
				return new ResponseEntity<Response<List<Inventory>>>(response, HttpStatus.NOT_FOUND);
			}
			
			response.setResult(list);
			response.setTotalElements(inventoryService.getAllProducts().size());
			response.setStatus(200);
			response.setstatusMessage("SUCCESS");
			return new ResponseEntity<Response<List<Inventory>>>(response, HttpStatus.OK);
		}
		catch(Exception e)
		{
			response.setstatusMessage(e.getMessage());
			return new ResponseEntity<Response<List<Inventory>>>(response, HttpStatus.NOT_FOUND);
		}	
	}

	
	//Get products based on their product id
	@GetMapping("/inventory/id")
	public ResponseEntity<Response<Inventory>> getProducts(@PathVariable(name="Prdouct_id") int id)
	{
		Inventory int1=null;
		Response<Inventory> response = new Response<Inventory>();
		response.setStatus(404);
		response.setstatusMessage("Data Not Found");
		
		try {
			LOGGER.debug("In get products controller using id...");
			int1=inventoryService.getProductsById(id);
			if(int1==null)
			{
				return new ResponseEntity<Response<Inventory>>(response, HttpStatus.NOT_FOUND);
			}
			
			response.setResult(int1);
			response.setTotalElements(inventoryService.getAllProducts().size());
			response.setStatus(200);
			response.setstatusMessage("SUCCESS");
			return new ResponseEntity<Response<Inventory>>(response, HttpStatus.OK);
		}
		catch(Exception e)
		{
			response.setstatusMessage(e.getMessage());
			return new ResponseEntity<Response<Inventory>>(response, HttpStatus.NOT_FOUND);
		}
	}
	
	
	//Get products based on their product code
	@GetMapping("/inventory/product")
	public ResponseEntity<Response<Inventory>> getProducts(@RequestParam String product_code)
	{
		Inventory int1=null;
		
		Response<Inventory> response = new Response<Inventory>();
		response.setStatus(404);
		response.setstatusMessage("Data Not Found");
		
		try {
			LOGGER.debug("In getProducts controller based on product code...");
			int1=inventoryService.getProducts(product_code);
			
			if(int1==null)
			{
				return new ResponseEntity<Response<Inventory>>(response, HttpStatus.NOT_FOUND);
			}
			
			response.setResult(int1);
			response.setTotalElements(inventoryService.getAllProducts().size());
			response.setStatus(200);
			response.setstatusMessage("SUCCESS");
			return new ResponseEntity<Response<Inventory>>(response, HttpStatus.OK);
		}
		catch(Exception e)
		{
			response.setstatusMessage(e.getMessage());
			return new ResponseEntity<Response<Inventory>>(response, HttpStatus.NOT_FOUND);
		}
	
	}
	
	
	//Get all categories based on store id
	@GetMapping("/inventory/categories")
	public ResponseEntity<Response<List<String>>> findAllbyID(@RequestParam int store_id)
	{
		List<String> list=null;
		Response<List<String>> response = new Response<List<String>>();
		response.setStatus(404);
		response.setstatusMessage("Data Not Found");
		try {
			LOGGER.debug("In get all categories controller using store id...");
			list=inventoryService.findAll(store_id);
			if(list.size()<=0)
			{
				return new ResponseEntity<Response<List<String>>>(response, HttpStatus.NOT_FOUND);
			}
			
			response.setResult(list);
			response.setTotalElements(inventoryService.getAllProducts().size());
			response.setStatus(200);
			response.setstatusMessage("SUCCESS");
			return new ResponseEntity<Response<List<String>>>(response, HttpStatus.OK);
		}
		catch(Exception e)
		{
			response.setstatusMessage(e.getMessage());
			return new ResponseEntity<Response<List<String>>>(response, HttpStatus.NOT_FOUND);
		}
	}
	
	
	//Get all Groups based on store id
	@GetMapping("/inventory/groups")
	public ResponseEntity<Response<List<String>>> findAllGroups(@RequestParam int store_id)
	{
		List<String> list = null;
		Response<List<String>> response = new Response<List<String>>();
		response.setStatus(404);
		response.setstatusMessage("Data Not Found");
		try {
			LOGGER.debug("In get all groups controller using store id...");
			list=inventoryService.findAllGroups(store_id);
		
			if(list.size()<=0)
			{
				return new ResponseEntity<Response<List<String>>>(response, HttpStatus.NOT_FOUND);
			}
		
			response.setResult(list);
			response.setTotalElements(inventoryService.getAllProducts().size());
			response.setStatus(200);
			response.setstatusMessage("SUCCESS");
			return new ResponseEntity<Response<List<String>>>(response, HttpStatus.OK);
		}
		catch(Exception e)
		{
			response.setstatusMessage(e.getMessage());
			return new ResponseEntity<Response<List<String>>>(response, HttpStatus.NOT_FOUND);
		}
	}
	
	
	//Get products based on categories
	@GetMapping("/inventory/category")
	public ResponseEntity<Response<List<Inventory>>> findAllbyCategory(@RequestParam(name="category_name") String name[])
	{
		List<Inventory> list=null;
		Response<List<Inventory>> response = new Response<List<Inventory>>();
		response.setStatus(404);
		response.setstatusMessage("Data Not Found");
		
		try {
			LOGGER.debug("In get products controller based on categories...");
			list=inventoryService.findByCategory(name);
			if(list.size()<=0)
			{
				return new ResponseEntity<Response<List<Inventory>>>(response, HttpStatus.NOT_FOUND);
			}
			
			response.setResult(list);
			response.setTotalElements(inventoryService.getAllProducts().size());
			response.setStatus(200);
			response.setstatusMessage("SUCCESS");
			return new ResponseEntity<Response<List<Inventory>>>(response, HttpStatus.OK);
		}
		catch(Exception e)
		{
			response.setstatusMessage(e.getMessage());
			return new ResponseEntity<Response<List<Inventory>>>(response, HttpStatus.NOT_FOUND);
		}	 
	}
	
	
	//To retrive products based on multiple values
	@GetMapping("/inventory/search")
	public ResponseEntity<Response<List<Inventory>>> findByMultipleValues(@RequestParam("product_name") String name,@RequestParam(name="category",required=false)String category[],@RequestParam(name="product_group",required=false)String group[],@RequestParam("store_id") int id)
	{
		List<Inventory> list= null;
		Response<List<Inventory>> response = new Response<List<Inventory>>();
		response.setStatus(404);
		response.setstatusMessage("Data Not Found");
		try {
			LOGGER.debug("In get products controller using multiple values...");
			if(category!= null && group!=null)
			{
				list = inventoryService.findByMultipleValues1(name,category,group,id);		
			}
			else if(category==null && group!=null)
			{
				list = inventoryService.findByMultipleValues2(name,group,id);
			}
			else if(group==null && category!=null)
			{
				list = inventoryService.findByMultipleValues3(name,category,id);
			}
			else 
			{
				list = inventoryService.findByMultipleValues4(name,id);
			}		
			
			if(list.size()<=0)
			{
				return new ResponseEntity<Response<List<Inventory>>>(response, HttpStatus.NOT_FOUND);
			}
		
			response.setResult(list);
			response.setTotalElements(inventoryService.getAllProducts().size());
			response.setStatus(200);
			response.setstatusMessage("SUCCESS");
			return new ResponseEntity<Response<List<Inventory>>>(response, HttpStatus.OK);
		}
		catch(Exception e)
		{
			response.setstatusMessage(e.getMessage());
			return new ResponseEntity<Response<List<Inventory>>>(response, HttpStatus.NOT_FOUND);
		}
	}
	
	
	//Get products based on Group
	@GetMapping("/inventory/group")
	public ResponseEntity<Response<List<Inventory>>> findAllbyGroups(@RequestParam(name="product_group") String group[])
	{
		List<Inventory> list=null;
		Response<List<Inventory>> response = new Response<List<Inventory>>();
		response.setStatus(404);
		response.setstatusMessage("Data Not Found");
		
		try {
			LOGGER.debug("In get products controller based on group...");
			 list=inventoryService.findByGroup(group);
			 
			 if(list.size()<=0)
				{
					return new ResponseEntity<Response<List<Inventory>>>(response, HttpStatus.NOT_FOUND);
				}
			
				response.setResult(list);
				response.setTotalElements(inventoryService.getAllProducts().size());
				response.setStatus(200);
				response.setstatusMessage("SUCCESS");
				return new ResponseEntity<Response<List<Inventory>>>(response, HttpStatus.OK);
			}
			catch(Exception e)
			{
				response.setstatusMessage(e.getMessage());
				return new ResponseEntity<Response<List<Inventory>>>(response, HttpStatus.NOT_FOUND);
			} 
	}
	
	
	//Remove particular product based on the product_id
	@DeleteMapping("/inventory/{id}")
	public ResponseEntity<Void> removeProducts(@PathVariable int id)
	{
		try{
			inventoryService.removeProducts(id);
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
		
}
