package com.example.RestAPI;
import javax.persistence.Entity;
import javax.persistence.Id;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
 @Entity
public class OnDemandAlarmTrigger {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
     private int id;
	 private String status;
		
	 
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
