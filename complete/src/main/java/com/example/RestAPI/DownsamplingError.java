package com.example.RestAPI;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
 @Entity
public class DownsamplingError {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
    private int project_id;
    private String start_time;
    private String end_time;
    private String solved;
    private String equipment;
    private String interval_num;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProject_id() {
		return project_id;
	}
	public void setProject_id(int project_id) {
		this.project_id = project_id;
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
	public String getSolved() {
		return solved;
	}
	public void setSolved(String solved) {
		this.solved = solved;
	}
	public String getEquipment() {
		return equipment;
	}
	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}
	public String getInterval_num() {
		return interval_num;
	}
	public void setInterval_num(String interval_num) {
		this.interval_num = interval_num;
	}
	
    
}  
