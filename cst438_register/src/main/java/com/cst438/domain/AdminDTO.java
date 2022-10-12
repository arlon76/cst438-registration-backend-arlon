/**		AdminDTO
 * 
 */
package com.cst438.domain;

/**
 * @author arlon
 *
 */
public class AdminDTO{
	
	private int admin_id;
	private String name;
	private String email;
	private int statusCode;
	private String status;
	
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
		return "AdminDTO: [admin_id=" + admin_id + ", name=" + name + ", email=" + email + ", statusCode="
				+ statusCode + ", status=" + status + "]";
	}
}
