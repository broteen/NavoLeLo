package com.nrift.banking.utility;

import java.io.Serializable;
import java.sql.Timestamp;

public class SearchAccountDTO  implements Serializable {
	private static final long serialVersionUID = 6297385302078200520L;	
	private String customerName;
	private long customerId;
	private long amount;
	private long panNumber;
	public SearchAccountDTO(String customerName,long customerId,long amount,long panNumber){
		this.customerName=customerName;
		this.customerId=customerId;
		this.amount=amount;
		this.panNumber=panNumber;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	public long getAmount(long amount) {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	public long getPanNumber(long panNumber) {
		return panNumber;
	}
	public void setPanNUmber(long panNumber) {
		this.panNumber = panNumber;
	}
}
