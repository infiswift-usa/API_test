package com.example.RestAPI;
import javax.persistence.Entity;
import javax.persistence.Id;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
 @Entity
public class LoginFailure {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	private String inserted_time;
    private String error_message;
    private String errorCode;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getInserted_time() {
		return inserted_time;
	}
	public void setInserted_time(String inserted_time) {
		this.inserted_time = inserted_time;
	}
	public String getError_message() {
		return error_message;
	}
	public void setError_message(String error_message) {
		this.error_message = error_message;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
   
    
}
