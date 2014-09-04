package com.nrift.banking.utility;

import java.io.Serializable;
import java.sql.Timestamp;

public class UserDetails implements Serializable{
	
	private static final long serialVersionUID = 6297385302078200511L;

	private long userId;
	private String userName;
	private Timestamp lastLoggedOn;
	private boolean isAdmin;
	private CustomerDetails customerDetails=null;
	private AdminDetails AdminDetails=null;

	public UserDetails(long userId, String userName,boolean isAdmin,Timestamp lastLoggedOn) {
		this.userId = userId;
		this.userName = userName;
		this.isAdmin = isAdmin;
		this.lastLoggedOn = lastLoggedOn;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Timestamp getLastLoggedOn() {
		return lastLoggedOn;
	}

	public void setLastLoggedOn(Timestamp lastLoggedOn) {
		this.lastLoggedOn = lastLoggedOn;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public CustomerDetails getCustomerDetails() {
		return customerDetails;
	}

	public void setCustomerDetails(CustomerDetails customerDetails) {
		this.customerDetails = customerDetails;
	}

	public AdminDetails getAdminDetails() {
		return AdminDetails;
	}

	public void setAdminDetails(AdminDetails adminDetails) {
		AdminDetails = adminDetails;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
