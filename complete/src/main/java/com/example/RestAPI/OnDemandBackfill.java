package com.example.RestAPI;
import javax.persistence.Entity;
import javax.persistence.Id;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
 @Entity
public class OnDemandBackfill {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	 private String start_time;
	 private String end_time;
	 private String interval_num;
	 private String status;
		
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public String getInterval_num() {
		return interval_num;
	}
	public void setInterval_num(String interval_num) {
		this.interval_num = interval_num;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
