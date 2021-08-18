package com.psl.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class StoreHoliday {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	
	@Column(name="holiday_name")
	private String holidayName;
	
	@Column(name="date")
	private Date date;

	public StoreHoliday() {
		super();
	}

	public StoreHoliday(int id, String holidayName, Date date) {
		super();
		this.id = id;
		this.holidayName = holidayName;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHolidayName() {
		return holidayName;
	}

	public void setHolidayName(String holidayName) {
		this.holidayName = holidayName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "StoreHoliday [id=" + id + ", holidayName=" + holidayName + ", date=" + date + "]";
	}
	
	
	
	
}
