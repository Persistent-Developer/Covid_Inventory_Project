package com.psl.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class OrderDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private double totalQuantities;
	private int quantity;
	public double getTotalQuantities() {
		return totalQuantities;
	}
	public void setTotalQuantities(double totalQuantities) {
		this.totalQuantities = totalQuantities;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public OrderDetails(double totalQuantities, int quantity) {
		
		this.totalQuantities = totalQuantities;
		this.quantity = quantity;
	}
	public OrderDetails() {
		
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "OrderDetails [totalQuantities=" + totalQuantities + ", quantity=" + quantity + "]";
	}
	
	
	
}
