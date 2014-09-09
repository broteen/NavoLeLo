package com.nrift.banking.utility;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class AccountDetails implements Serializable{
	private static final long serialVersionUID = 6297385302078200515L;
	
	private long accountNo;
	private String accountType;
	private long balance;
	private Timestamp updatedTime;
	private List<TransactionHistoryDetails> transactionHistoryDetailsList=null;
	
	public AccountDetails(long accountNo, String accountType, long balance, Timestamp updatedTime) {
		
		this.accountNo=accountNo;
		this.accountType=accountType;
		this.balance=balance;
		this.updatedTime=updatedTime;
	}
	/**
	 * @return the accountNo
	 */
	public long getAccountNo() {
		return accountNo;
	}
	/**
	 * @param accountNo the accountNo to set
	 */
	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}
	/**
	 * @return the accountType
	 */
	public String getAccountType() {
		return accountType;
	}
	/**
	 * @param accountType the accountType to set
	 */
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	/**
	 * @return the balance
	 */
	public long getBalance() {
		return balance;
	}
	/**
	 * @param balance the balance to set
	 */
	public void setBalance(long balance) {
		this.balance = balance;
	}
	
	public Timestamp getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Timestamp updatedTime) {
		this.updatedTime = updatedTime;
	}
	public List<TransactionHistoryDetails> getTransactionHistoryDetailsList() {
		return transactionHistoryDetailsList;
	}
	/**
	 * @param accountList the accountList to set
	 */
	public void setTransactionHistoryDetailsList(List<TransactionHistoryDetails> list) {
		this.transactionHistoryDetailsList = transactionHistoryDetailsList;
	}


}



