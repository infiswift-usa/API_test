package com.example.RestAPI;
import javax.persistence.Entity;
import javax.persistence.Id;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
 @Entity
public class OnDemandAlarm {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
     private int id;
	 private String setting;
	 private String inserted_time;
	 private String status;
		
	 public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSetting() {
		return setting;
	}
	public void setSetting(String setting) {
		this.setting = setting;
	}
	public String getInserted_time() {
		return inserted_time;
	}
	public void setInserted_time(String inserted_time) {
		this.inserted_time = inserted_time;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
