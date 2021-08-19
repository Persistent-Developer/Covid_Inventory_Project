package com.psl.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.psl.dao.IInventoryDao;
import com.psl.entity.Inventory;
import com.psl.entity.User;
import com.psl.util.ExcelUtils;

@Service("service")
public class InventoryService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(InventoryService.class);

	@Autowired
	private IInventoryDao inventoryDao;
	
	public Inventory addProducts(Inventory inventory) throws Exception
	{
		return inventoryDao.save(inventory);
	}
	
	
	public Inventory updateProducts(int id,Inventory inventory) throws Exception
	{
		Inventory item= inventoryDao.findById(id).get();
		
		if(inventory.getProductName().equals(item.getProductName()) &&
				inventory.getProductCode().equals(item.getProductCode()) &&
				inventory.getCategory().equals(item.getCategory()) &&
				inventory.getProductGroup().equals(item.getProductGroup()))
		{
			inventory.setStock(inventory.getStock()+item.getStock());
			return inventoryDao.save(inventory);
		}
		else
		{
			item.setCategory(inventory.getCategory());
			item.setProductGroup(inventory.getProductGroup());
			item.setProductName(inventory.getProductName());
			item.setProductCode(inventory.getProductCode());
			item.setItemType(inventory.getItemType());
			item.setPrice(inventory.getPrice());
			item.setStock(inventory.getStock());
			item.setYearlyQuotaPerUser(inventory.getYearlyQuotaPerUser());
			item.setMonthlyQuotaPerUser(inventory.getMonthlyQuotaPerUser());
			item.setLowStockIndicator(inventory.getLowStockIndicator());

			return inventoryDao.save(item);
		}
	}
	
	
	public void store(MultipartFile file) {
		try {
			LOGGER.debug("In store() funtion of inventory service ....");
			ExcelUtils util = new ExcelUtils(inventoryDao);
			util.parseInventoryExcelFile(file.getInputStream());
			
        } catch (IOException e) {
        	LOGGER.info("Error while parsing inventory excel file ...");
        	throw new RuntimeException("FAIL! -> message = " + e.getMessage());
        }
	}
	

	public List<Inventory> getAllProducts() throws Exception
	{
		return inventoryDao.getAllProducts();
	}
	
	
	public Inventory getProducts(String code) throws Exception
	{
		return inventoryDao.findByProductCode(code);
	}
	
	
	public Inventory getProductsById(int id) throws Exception
	{	
		return inventoryDao.findById(id).get();
	}
	
	
	public void removeProducts(int id) throws Exception
	{
		inventoryDao.deleteById(id);
	}
	
	public List<String> findAll(int id) throws Exception
	{
		return inventoryDao.findAllbyID(id);
	}
	
	public List<String> findAllGroups(int id) throws Exception
	{
		return inventoryDao.findAllGroups(id);
	}
	
	public List<Inventory> findByCategory(String name[]) throws Exception
	{
		List<Inventory> ilist=new ArrayList<>();
		
		for(String nam:name)
		{
			List<Inventory> list=inventoryDao.findByCategory(nam);
		
			for(Inventory i:list)
			{
				ilist.add(i);
			}
		}
		
		return ilist;	
	}
	
	
	public List<Inventory> findByGroup(String name[]) throws Exception
	{
		List<Inventory> ilist=new ArrayList<>();
		
		for(String nam:name)
		{
			List<Inventory> list=inventoryDao.findByGroup(nam);
		
			for(Inventory i:list)
			{
				ilist.add(i);
			}
		}
		
		return ilist;	
	}
	
	public List<Inventory> findByMultipleValues1(String name,String category[],String group[],int id) throws Exception
	{
		List<Inventory> ilist=new ArrayList<>();
		
		for(String cat:category)
		{
			for(String grp:group)
			{
				List<Inventory> list=inventoryDao.findByMultipleValues1(name,cat,grp,id);
				
				for(Inventory i:list)
				{
					ilist.add(i);
				}
			}
		}
		return ilist;
	}
	
	
	public List<Inventory> findByMultipleValues2(String name,String group[],int id) throws Exception
	{
		List<Inventory> ilist=new ArrayList<>();
		
		for(String grp:group)
		{
			List<Inventory> list=inventoryDao.findByMultipleValues2(name,grp,id);
				
			for(Inventory i:list)
			{
				ilist.add(i);
			}
		}
		return ilist;
	}
	
	public List<Inventory> findByMultipleValues3(String name,String category[],int id) throws Exception
	{
		List<Inventory> ilist=new ArrayList<>();
		
		for(String cat:category)
		{
			List<Inventory> list=inventoryDao.findByMultipleValues3(name,cat,id);
				
			for(Inventory i:list)
			{
				ilist.add(i);
			}
		}
		return ilist;
	}
	
	public List<Inventory> findByMultipleValues4(String name,int id) throws Exception
	{
		return inventoryDao.findByMultipleValues4(name,id);
	}
	
	
}
