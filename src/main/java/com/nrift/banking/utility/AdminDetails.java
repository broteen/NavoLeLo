package com.nrift.banking.utility;

import java.io.Serializable;

public class AdminDetails implements Serializable {
	private static final long serialVersionUID = 6297385302078200514L;
	
	private long adminId;
	private String name;
	private String email;
	/**
	 * @return the adminId
	 */
	
	
	public AdminDetails(long adminId,String name,String email) 
	{
		this.adminId=adminId;
		this.name=name;
		this.email=email;
	}
	
	
	public long getAdminId() {
		return adminId;
	}
	/**
	 * @param adminId the adminId to set
	 */
	public void setAdminId(long adminId) {
		this.adminId = adminId;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

}
