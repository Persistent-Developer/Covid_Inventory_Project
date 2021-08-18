package com.psl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class StoreBreaks {

	    @Id
	    @GeneratedValue(strategy= GenerationType.IDENTITY)
	    private Integer id;

	    @Column(name = "break_from")
	    private String breakFrom;

	    @Column(name = "break_to")
	    private String breakTo;

	    @Column(name = "break_type")
	    private String breakType;

	    public Integer getId() {
	        return id;
	    }

	    public void setId(Integer id) {
	        this.id = id;
	    }

	    public String getBreakFrom() {
	        return breakFrom;
	    }

	    public void setBreakFrom(String breakFrom) {
	        this.breakFrom = breakFrom;
	    }

	    public String getBreakTo() {
	        return breakTo;
	    }

	    public void setBreakTo(String breakTo) {
	        this.breakTo = breakTo;
	    }

	    public String getBreakType() {
	        return breakType;
	    }

	    public void setBreakType(String breakType) {
	        this.breakType = breakType;
	    }

		@Override
		public String toString() {
			return "BreakTimings [id=" + id + ", breakFrom=" + breakFrom + ", breakTo=" + breakTo + ", breakType="
					+ breakType + "]";
		}

		public StoreBreaks(Integer id, String breakFrom, String breakTo, String breakType) {
			super();
			this.id = id;
			this.breakFrom = breakFrom;
			this.breakTo = breakTo;
			this.breakType = breakType;
		}

		public StoreBreaks() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		
	
	
}
