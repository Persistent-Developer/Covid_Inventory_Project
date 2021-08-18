package com.psl.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Orders {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderId;
	private String orderStatus;
	private String deliveryDate;
	private String timeSlotStart;
	private String timeSlotEnd;
	private String contact;
	
	@ManyToMany(fetch=FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "odList")
	List<Inventory> invList;
	
	public Orders() {
	}

	public Orders(int orderId, String orderStatus, String deliveryDate, String timeSlotStart, String timeSlotEnd,
			String contact, List<Inventory> invList) {
		super();
		this.orderId = orderId;
		this.orderStatus = orderStatus;
		this.deliveryDate = deliveryDate;
		this.timeSlotStart = timeSlotStart;
		this.timeSlotEnd = timeSlotEnd;
		this.contact = contact;
		this.invList = invList;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getTimeSlotStart() {
		return timeSlotStart;
	}

	public void setTimeSlotStart(String timeSlotStart) {
		this.timeSlotStart = timeSlotStart;
	}

	public String getTimeSlotEnd() {
		return timeSlotEnd;
	}

	public void setTimeSlotEnd(String timeSlotEnd) {
		this.timeSlotEnd = timeSlotEnd;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public List<Inventory> getInvList() {
		return invList;
	}

	public void setInvList(List<Inventory> invList) {
		this.invList = invList;
	}

	@Override
	public String toString() {
		return "Orders [orderId=" + orderId + ", orderStatus=" + orderStatus + ", deliveryDate=" + deliveryDate
				+ ", timeSlotStart=" + timeSlotStart + ", timeSlotEnd=" + timeSlotEnd + ", contact=" + contact
				+ ", invList=" + invList + "]";
	}
}
