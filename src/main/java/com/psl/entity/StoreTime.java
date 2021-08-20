package com.psl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class StoreTime {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	
	@Column(name="day")
	private String day;
	
	@Column(name="start_time")
	private String startTime;
	
	@Column(name="end_time")
	private String endTime;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getstartTime() {
		return startTime;
	}
	public void setstartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getendTime() {
		return endTime;
	}
	public void setendTime(String endTime) {
		this.endTime = endTime;
	}
	public StoreTime(int id, String day, String startTime, String endTime) {
		super();
		this.id = id;
		this.day = day;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	public StoreTime() {
		super();
	}
	@Override
	public String toString() {
		return "StoreTime [id=" + id + ", day=" + day + ", startTime=" + startTime + ", endTime=" + endTime + "]";
	}
	
	
}



