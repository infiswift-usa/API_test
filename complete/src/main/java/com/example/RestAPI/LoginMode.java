package com.example.RestAPI;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
 @Entity
public class LoginMode {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	private String mode;
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
}
