
package com.psl.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","Handler"})
public class Store {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="is_active")
	private String isActive;
	
	@Column(name="no_of_timeslots")
	private int noOfTimeslots;
	
	@Column(name="slotduration")
	private int slotDuration;
	
	@OneToMany(targetEntity=StoreTime.class, cascade= CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="store",referencedColumnName="id")
	private Set<StoreTime> storeTimings;
	
	@OneToMany(targetEntity=StoreBreaks.class, cascade= CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="store",referencedColumnName="id")
	private Set<StoreBreaks> breakTimings;

	@OneToMany(targetEntity=StoreHoliday.class, cascade= CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="store",referencedColumnName="id")
	private Set<StoreHoliday> storeHolidays;

	public Store() {
		super();
	}

	

	public Store(int id, String name, String isActive, int noOfTimeslots, int slotDuration, Set<StoreTime> storeTimings,
			Set<StoreBreaks> breakTimings, Set<StoreHoliday> storeHolidays) {
		super();
		this.id = id;
		this.name = name;
		this.isActive = isActive;
		this.noOfTimeslots = noOfTimeslots;
		this.slotDuration = slotDuration;
		this.storeTimings = storeTimings;
		this.breakTimings = breakTimings;
		this.storeHolidays = storeHolidays;
	}



	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getIsActive() {
		return isActive;
	}


	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}


	public int getNoOfTimeslots() {
		return noOfTimeslots;
	}


	public void setNoOfTimeslots(int noOfTimeslots) {
		this.noOfTimeslots = noOfTimeslots;
	}


	public int getSlotDuration() {
		return slotDuration;
	}


	public void setSlotDuration(int slotDuration) {
		this.slotDuration = slotDuration;
	}


	public Set<StoreTime> getStoreTimings() {
		return storeTimings;
	}


	public void setStoreTimings(Set<StoreTime> storeTimings) {
		this.storeTimings = storeTimings;
	}


	public Set<StoreBreaks> getBreakTimings() {
		return breakTimings;
	}


	public void setBreakTimings(Set<StoreBreaks> breakTimings) {
		this.breakTimings = breakTimings;
	}


	public Set<StoreHoliday> getStoreHolidays() {
		return storeHolidays;
	}


	public void setStoreHolidays(Set<StoreHoliday> storeHolidays) {
		this.storeHolidays = storeHolidays;
	}

	@Override
	public String toString() {
		return "Store [id=" + id + ", name=" + name + ", isActive=" + isActive + ", noOfTimeslots=" + noOfTimeslots
				+ ", slotDuration=" + slotDuration + ", storeTimings=" + storeTimings + ", breakTimings=" + breakTimings
				+ ", storeHolidays=" + storeHolidays + "]";
	}


	
}
