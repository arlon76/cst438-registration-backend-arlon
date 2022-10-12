package com.cst438.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Admin {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int admin_id;
	private String name;
	private String email;
	private int statusCode;
	private String status;
	
	public Admin() {
		super();
	}
	
	public Admin(String name, String email, String status, int statusCode) {
		super();
		this.name=name;this.email=email;this.status=status;this.statusCode=statusCode;
	}
	
	public int getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Admin [admin_id=" + admin_id + ", name=" + name + ", email=" + email + ", statusCode="
				+ statusCode + ", status=" + status + "]";
	}

}
