package com.psl.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import org.apache.poi.ss.usermodel.Cell;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.psl.controller.UserController;
import com.psl.dao.IInventoryDao;
import com.psl.dao.IUserDAO;
import com.psl.entity.Inventory;
import com.psl.entity.Role;
import com.psl.entity.Store;
import com.psl.entity.User;



public class ExcelUtils {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ExcelUtils.class);
	
	private IInventoryDao dao;
	private IUserDAO udao;
	
	public ExcelUtils(IInventoryDao dao) {
		this.dao = dao;
	}
	
	public ExcelUtils(IUserDAO udao) {
		this.udao = udao;
	}


	public  List<Inventory> parseInventoryExcelFile(InputStream is) {
		List<Inventory> inventoryList= new ArrayList<>();
		Inventory item=new Inventory();
		try {
			LOGGER.debug("In parseInventoryExcelFile() method.....");
    		Workbook workbook = new XSSFWorkbook(is);
    		Sheet sheet = workbook.getSheet("Inventory");
    		//System.out.println("**");
    		System.out.println("Sheet is present or not : " + sheet.getSheetName());
    		Iterator<Row> rows = sheet.iterator();	
    		//List<Inventory> lstInventorys = new ArrayList<Inventory>();
    		
			int flag=0;
    		
    		int rowNumber = 0;
    		while (rows.hasNext()) {
    			System.out.println("**");
    			Row currentRow = rows.next();
    			
    			// skip header
    			if(rowNumber == 0) {
    				rowNumber++;
    				continue;
    			}
    			
    			Iterator<Cell> cellsInRow = currentRow.iterator();

    			Inventory invt = new Inventory();
    			
    			int cellIndex = 0;
    			while (cellsInRow.hasNext()) {
    				Cell currentCell = cellsInRow.next();
    			
    				if(cellIndex==0) { 
    					//System.out.println("***");
    					try {
    						
    						
    						String code = currentCell.getStringCellValue();
    						System.out.println("code = " + code);
    						
    						
	    					Inventory i = dao.findByProductCode1(code);
	    					
	    					System.out.println("Name = " + i.getProductName());
	    					
	    					if(i!=null) {
	    						//System.out.println("--");
	    						
	    						Cell st = workbook.getSheetAt(0).getRow(rowNumber).getCell(3);
	    						System.out.println("Stock = " + st.getNumericCellValue());
  						
	    						int updateStock = (int) st.getNumericCellValue() + i.getStock();
	    						System.out.println("--");
	    						i.setStock(updateStock);
	    						item=dao.save(i);
	    						inventoryList.add(item);
	    						flag=1;
	    						break;
	    					}
    					} catch(Exception e) {
    						System.out.println("In exception...");
    					}
    					
    					flag=0;
    					System.out.println(currentCell.getStringCellValue());
    					invt.setProductCode(currentCell.getStringCellValue());
    				} else if(cellIndex==1) { 
    					flag=0;
    					System.out.println(currentCell.getStringCellValue());
    					invt.setProductName(currentCell.getStringCellValue());
    				} else if(cellIndex==2) { 
    					flag=0;
    					System.out.println(currentCell.getNumericCellValue());
    					invt.setPrice((double)currentCell.getNumericCellValue());
    				} else if(cellIndex==3) { 
    					flag=0;
    					System.out.println(currentCell.getNumericCellValue());
    					invt.setStock((int) currentCell.getNumericCellValue());
    				} else if(cellIndex==4) { 
    					flag=0;
    					System.out.println(currentCell.getStringCellValue());
    					invt.setProductGroup(currentCell.getStringCellValue());
    				}else if(cellIndex==5) {
    					flag=0;
    					System.out.println(currentCell.getStringCellValue());
    					invt.setCategory(currentCell.getStringCellValue());
    				} else if(cellIndex==6) { 
    					flag=0;
    					System.out.println(currentCell.getNumericCellValue());
    					invt.setLowStockIndicator((int) currentCell.getNumericCellValue());
    				} else if(cellIndex==7) { 
    					flag=0;
    					System.out.println(currentCell.getStringCellValue());
    					invt.setInStock(currentCell.getStringCellValue());
    				} else if(cellIndex==8) { 
    					flag=0;
    					System.out.println(currentCell.getStringCellValue());
    					invt.setItemType(currentCell.getStringCellValue());
    				} else if(cellIndex==9) { 
    					flag=0;
    					System.out.println(currentCell.getNumericCellValue());
    					String temp = (Integer.valueOf((int) currentCell.getNumericCellValue())).toString();
    					invt.setMonthlyQuotaPerUser(temp);
    				} else if(cellIndex==10) { 
    					flag=0;
    					switch(currentCell.getCellTypeEnum()) {

    					case NUMERIC: 
        					System.out.println(currentCell.getNumericCellValue());
        	
        					invt.setYearlyQuotaPerUser(Integer.toString((int) currentCell.getNumericCellValue()));
        					break;
    					case STRING:
    						System.out.println(currentCell.getStringCellValue());
    						invt.setYearlyQuotaPerUser(currentCell.getStringCellValue());
    						break;
    					}
    					
    				} else if(cellIndex==11) { 
    					flag=0;
    					System.out.println(currentCell.getNumericCellValue());
    					Store st = new Store();
    					st.setId((int)currentCell.getNumericCellValue());
    					invt.setStore(st);
    				}
    				
    				cellIndex++;
    			}
    	
    			if(flag==0)
    			{	
    				item=dao.save(invt);
    				inventoryList.add(item);
    				
    			}
    			
    			rowNumber++;
    		}
    		

    		workbook.close();
    		return inventoryList;

        } catch (IOException e) {
        	throw new RuntimeException("FAIL! -> message = " + e.getMessage());
        }
	}
	
	public void parseUserExcelFile(InputStream is) {
		try {
			LOGGER.debug("In parseUserExcelFile() method.....");
    		Workbook workbook = new XSSFWorkbook(is);
    		
    			
    		Sheet sheet = workbook.getSheet("Sheet1");
    		//System.out.println("**");
    		System.out.println("Sheet is present or not : " + sheet.getSheetName());
    		Iterator<Row> rows = sheet.iterator();
    		
    		List<User> lstUsers = new ArrayList<User>();
    		int flag=0;
    		
    		int rowNumber = 0;
    		while (rows.hasNext()) {
    			System.out.println("**");
    			Row currentRow = rows.next();
    			
    			// skip header
    			if(rowNumber == 0) {
    				rowNumber++;
    				continue;
    			}
    			
    			Iterator<Cell> cellsInRow = currentRow.iterator();

    			User usr = new User();
    			//Store st = new Store();
    			
    			int cellIndex = 0;
    			while (cellsInRow.hasNext()) {
    				Cell currentCell = cellsInRow.next();
    			
    				if(cellIndex==0) { 
    					//System.out.println("***");
    					
    					try {
    						
    						String number = currentCell.getStringCellValue();
    						System.out.println("number = " + number);
    						
	    					User u = udao.findByPhNumber(number);
	    					
	    					
	    					
	    					if(u!=null) {
	    						
	    						System.out.println("Name = " + u.getUserName() + " already present");
	    						flag=1;
	    						break;
	    					}
    					} catch(Exception e) {
    						System.out.println("In exception...");
    					}
    					
    					flag=0;
    					System.out.println(currentCell.getStringCellValue());
    					usr.setPhNumber(currentCell.getStringCellValue());
    				} else if(cellIndex==1) { 
    					flag=0;
    					System.out.println(currentCell.getStringCellValue());
    					usr.setUserName(currentCell.getStringCellValue());
    				} else if(cellIndex==2) { 
    					flag=0;
    					System.out.println(currentCell.getNumericCellValue());
    					Role role = new Role();
    					role.setRoleId((int)currentCell.getNumericCellValue());
    					usr.setRole(role);
    				} else if(cellIndex==3) { 
    					flag=0;
    					System.out.println(currentCell.getStringCellValue());
    					usr.setStatus(currentCell.getStringCellValue());
    				} else if(cellIndex==4) { 
    					flag=0;
    					System.out.println(currentCell.getStringCellValue());
    					usr.setCategory(currentCell.getStringCellValue());
    				}else if(cellIndex==5) {
    					flag=0;
    					System.out.println(currentCell.getNumericCellValue());
    					usr.setPurchaseLimitPerYear((int)currentCell.getNumericCellValue());
    				} else if(cellIndex==6) { 
    					flag=0;
    					System.out.println(currentCell.getNumericCellValue());
    					usr.setPurchaseLimitPerMonth((int) currentCell.getNumericCellValue());
    				} else if(cellIndex==7) { 
    					flag=0;
    					System.out.println(currentCell.getStringCellValue());
    					usr.setEmail(currentCell.getStringCellValue());
    				} else if(cellIndex==8) { 
    					flag=0;
    					System.out.println(currentCell.getStringCellValue());
    					usr.setPassword(currentCell.getStringCellValue());
    				}
    				
    				cellIndex++;
    			}
    			if(flag==0)
    				udao.save(usr);
    			rowNumber++;
    			
    			//lstUsers.add(usr);
    		}
    		
    		// Close WorkBook
    		workbook.close();
    		
    		//return lstUsers;
        } catch (IOException e) {
        	throw new RuntimeException("FAIL! -> message = " + e.getMessage());
        }
	}
}
