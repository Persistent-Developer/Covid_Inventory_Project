package com.psl.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import com.psl.dao.IInventoryDao;
import com.psl.dao.IUserDAO;
import com.psl.entity.Inventory;
import com.psl.entity.Role;
import com.psl.entity.Store;
import com.psl.entity.User;



public class ExcelUtils {
	

	/*
	 * private IInventoryDao dao; private IUserDAO udao;
	 * 
	 * public ExcelUtils(IInventoryDao dao) { this.dao = dao; }
	 * 
	 * public ExcelUtils(IUserDAO udao) { this.udao = udao; }
	 * 
	 * public static ByteArrayInputStream inventorysToExcel(List<Inventory>
	 * inventorys) throws IOException { String[] COLUMNs = {"product_id",
	 * "product_name", "price",
	 * "stock","product_group","category","low_stock_indicator","in_stock",
	 * "item_type","monthly_quota_per_user","yearly_quota_per_user"}; try( Workbook
	 * workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new
	 * ByteArrayOutputStream(); ){ CreationHelper createHelper =
	 * workbook.getCreationHelper();
	 * 
	 * Sheet sheet = workbook.createSheet("Inventory");
	 * 
	 * Font headerFont = workbook.createFont(); headerFont.setBold(true);
	 * headerFont.setColor(IndexedColors.BLUE.getIndex());
	 * 
	 * CellStyle headerCellStyle = workbook.createCellStyle();
	 * headerCellStyle.setFont(headerFont);
	 * 
	 * // Row for Header Row headerRow = sheet.createRow(0);
	 * 
	 * // Header for (int col = 0; col < COLUMNs.length; col++) { Cell cell =
	 * headerRow.createCell(col); cell.setCellValue(COLUMNs[col]);
	 * cell.setCellStyle(headerCellStyle); }
	 * 
	 * // CellStyle for Age // CellStyle ageCellStyle = workbook.createCellStyle();
	 * //
	 * ageCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("#"));
	 * 
	 * int rowIdx = 1; for (Inventory inventory : inventorys) { Row row =
	 * sheet.createRow(rowIdx++);
	 * 
	 * row.createCell(0).setCellValue(inventory.getProduct_id());
	 * row.createCell(1).setCellValue(inventory.getProduct_name());
	 * row.createCell(2).setCellValue(inventory.getPrice());
	 * row.createCell(2).setCellValue(inventory.getStock());
	 * row.createCell(2).setCellValue(inventory.getProduct_group());
	 * row.createCell(2).setCellValue(inventory.getCategory());
	 * row.createCell(2).setCellValue(inventory.getlow_stock_indicator());
	 * row.createCell(2).setCellValue(inventory.getIn_stock());
	 * row.createCell(2).setCellValue(inventory.getItem_type());
	 * row.createCell(2).setCellValue(inventory.getMonthly_quota_per_user());
	 * row.createCell(2).setCellValue(inventory.getYearly_quota_per_user());
	 * 
	 * }
	 * 
	 * workbook.write(out); return new ByteArrayInputStream(out.toByteArray()); } }
	 * 
	 * 
	 * public void parseInventoryExcelFile(InputStream is) { try { Workbook workbook
	 * = new XSSFWorkbook(is); Sheet sheet = workbook.getSheet("Inventory");
	 * //System.out.println("**"); System.out.println("Sheet is present or not : " +
	 * sheet.getSheetName()); Iterator<Row> rows = sheet.iterator(); List<Inventory>
	 * lstInventorys = new ArrayList<Inventory>();
	 * 
	 * int flag=0;
	 * 
	 * int rowNumber = 0; while (rows.hasNext()) { System.out.println("**"); Row
	 * currentRow = rows.next();
	 * 
	 * // skip header if(rowNumber == 0) { rowNumber++; continue; }
	 * 
	 * Iterator<Cell> cellsInRow = currentRow.iterator();
	 * 
	 * Inventory invt = new Inventory();
	 * 
	 * int cellIndex = 0; while (cellsInRow.hasNext()) { Cell currentCell =
	 * cellsInRow.next();
	 * 
	 * if(cellIndex==0) { //System.out.println("***"); try {
	 * 
	 * 
	 * String code = currentCell.getStringCellValue(); System.out.println("code = "
	 * + code);
	 * 
	 * 
	 * Inventory i = dao.findByProduct_code1(code);
	 * 
	 * System.out.println("Name = " + i.getProduct_name());
	 * 
	 * if(i!=null) { //System.out.println("--");
	 * 
	 * Cell st = workbook.getSheetAt(0).getRow(rowNumber).getCell(3);
	 * System.out.println("Stock = " + st.getNumericCellValue());
	 * 
	 * int updateStock = (int) st.getNumericCellValue() + i.getStock();
	 * System.out.println("--"); i.setStock(updateStock); dao.save(i); flag=1;
	 * break; } } catch(Exception e) { System.out.println("In exception..."); }
	 * 
	 * flag=0; System.out.println(currentCell.getStringCellValue());
	 * invt.setProduct_code(currentCell.getStringCellValue()); } else
	 * if(cellIndex==1) { flag=0;
	 * System.out.println(currentCell.getStringCellValue());
	 * invt.setProduct_name(currentCell.getStringCellValue()); } else
	 * if(cellIndex==2) { flag=0;
	 * System.out.println(currentCell.getNumericCellValue());
	 * invt.setPrice((double)currentCell.getNumericCellValue()); } else
	 * if(cellIndex==3) { flag=0;
	 * System.out.println(currentCell.getNumericCellValue()); invt.setStock((int)
	 * currentCell.getNumericCellValue()); } else if(cellIndex==4) { flag=0;
	 * System.out.println(currentCell.getStringCellValue());
	 * invt.setProduct_group(currentCell.getStringCellValue()); }else
	 * if(cellIndex==5) { flag=0;
	 * System.out.println(currentCell.getStringCellValue());
	 * invt.setCategory(currentCell.getStringCellValue()); } else if(cellIndex==6) {
	 * flag=0; System.out.println(currentCell.getNumericCellValue());
	 * invt.setlow_stock_indicator((int) currentCell.getNumericCellValue()); } else
	 * if(cellIndex==7) { flag=0;
	 * System.out.println(currentCell.getStringCellValue());
	 * invt.setIn_stock(currentCell.getStringCellValue()); } else if(cellIndex==8) {
	 * flag=0; System.out.println(currentCell.getStringCellValue());
	 * invt.setItem_type(currentCell.getStringCellValue()); } else if(cellIndex==9)
	 * { flag=0; System.out.println(currentCell.getNumericCellValue()); String temp
	 * = (Integer.valueOf((int) currentCell.getNumericCellValue())).toString();
	 * invt.setMonthly_quota_per_user(temp); } else if(cellIndex==10) { flag=0; //
	 * switch(currentCell.getCellType()) { // // case NUMERIC: //
	 * System.out.println(currentCell.getNumericCellValue()); // //
	 * invt.setYearly_quota_per_user(Integer.toString((int)
	 * currentCell.getNumericCellValue())); // break; // case STRING: //
	 * System.out.println(currentCell.getStringCellValue()); //
	 * invt.setYearly_quota_per_user(currentCell.getStringCellValue()); // break; //
	 * }
	 * 
	 * } else if(cellIndex==11) { flag=0;
	 * System.out.println(currentCell.getNumericCellValue()); Store st = new
	 * Store(); st.setId((int)currentCell.getNumericCellValue()); invt.setstore(st);
	 * }
	 * 
	 * cellIndex++; }
	 * 
	 * if(flag==0) dao.save(invt); rowNumber++; }
	 * 
	 * 
	 * workbook.close();
	 * 
	 * 
	 * } catch (IOException e) { throw new RuntimeException("FAIL! -> message = " +
	 * e.getMessage()); } }
	 * 
	 * public void parseUserExcelFile(InputStream is) { try { Workbook workbook =
	 * new XSSFWorkbook(is);
	 * 
	 * 
	 * Sheet sheet = workbook.getSheet("Sheet1"); //System.out.println("**");
	 * System.out.println("Sheet is present or not : " + sheet.getSheetName());
	 * Iterator<Row> rows = sheet.iterator();
	 * 
	 * List<User> lstUsers = new ArrayList<User>(); int flag=0;
	 * 
	 * int rowNumber = 0; while (rows.hasNext()) { System.out.println("**"); Row
	 * currentRow = rows.next();
	 * 
	 * // skip header if(rowNumber == 0) { rowNumber++; continue; }
	 * 
	 * Iterator<Cell> cellsInRow = currentRow.iterator();
	 * 
	 * User usr = new User(); //Store st = new Store();
	 * 
	 * int cellIndex = 0; while (cellsInRow.hasNext()) { Cell currentCell =
	 * cellsInRow.next();
	 * 
	 * if(cellIndex==0) { //System.out.println("***");
	 * 
	 * try {
	 * 
	 * String number = currentCell.getStringCellValue();
	 * System.out.println("number = " + number);
	 * 
	 * User u = udao.findByPhNumber(number);
	 * 
	 * 
	 * 
	 * if(u!=null) {
	 * 
	 * System.out.println("Name = " + u.getUserName() + " already present"); flag=1;
	 * break; } } catch(Exception e) { System.out.println("In exception..."); }
	 * 
	 * flag=0; System.out.println(currentCell.getStringCellValue());
	 * usr.setPhNumber(currentCell.getStringCellValue()); } else if(cellIndex==1) {
	 * flag=0; System.out.println(currentCell.getStringCellValue());
	 * usr.setUserName(currentCell.getStringCellValue()); } else if(cellIndex==2) {
	 * flag=0; System.out.println(currentCell.getNumericCellValue()); Role role =
	 * new Role(); role.setRoleId((int)currentCell.getNumericCellValue());
	 * usr.setRole(role); } else if(cellIndex==3) { flag=0;
	 * System.out.println(currentCell.getStringCellValue());
	 * usr.setStatus(currentCell.getStringCellValue()); } else if(cellIndex==4) {
	 * flag=0; System.out.println(currentCell.getStringCellValue());
	 * usr.setCategory(currentCell.getStringCellValue()); }else if(cellIndex==5) {
	 * flag=0; System.out.println(currentCell.getNumericCellValue());
	 * usr.setPurchaseLimitPerYear((int)currentCell.getNumericCellValue()); } else
	 * if(cellIndex==6) { flag=0;
	 * System.out.println(currentCell.getNumericCellValue());
	 * usr.setPurchaseLimitPerMonth((int) currentCell.getNumericCellValue()); } else
	 * if(cellIndex==7) { flag=0;
	 * System.out.println(currentCell.getStringCellValue());
	 * usr.setEmail(currentCell.getStringCellValue()); } else if(cellIndex==8) {
	 * flag=0; System.out.println(currentCell.getStringCellValue());
	 * usr.setPassword(currentCell.getStringCellValue()); }
	 * 
	 * cellIndex++; } if(flag==0) udao.save(usr); rowNumber++;
	 * 
	 * //lstUsers.add(usr); }
	 * 
	 * // Close WorkBook workbook.close();
	 * 
	 * //return lstUsers; } catch (IOException e) { throw new
	 * RuntimeException("FAIL! -> message = " + e.getMessage()); } }
	 */
}
