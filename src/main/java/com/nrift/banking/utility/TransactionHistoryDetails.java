package com.nrift.banking.utility;

import java.io.Serializable;

import java.sql.Timestamp;

public class TransactionHistoryDetails implements Serializable{
	private static final long serialVersionUID = 6297385302078200515L;
	
	private long transactionRef;
	
	private long crAccNum;
	private long drAccNum;
	private long amount;

	private Timestamp transactionTime;

	public TransactionHistoryDetails (long transactionRef, Timestamp transactionTime, long crAccNum, long drAccNum, long amount) {
		
		this.transactionRef=transactionRef;
		this.transactionTime=transactionTime;
		this.crAccNum=crAccNum;
		this.drAccNum=drAccNum;
		this.amount=amount;
	}
	/**
	 * @return the accountNo
	 */
	public long getTransactionRef() {
		return transactionRef;
	}
	/**
	 * @param accountNo the accountNo to set
	 */
	public void setTransactionRefo(long transactionRef ) {
		this.transactionRef = transactionRef;
	}
	/**
	 * @return the accountType
	 */
	public Timestamp getTimeStamp() {
		return transactionTime;
	}
	/**
	 * @param accountType the accountType to set
	 */
	public void setTimeStamp(int timeStamp) {
		this.transactionTime=transactionTime;
	}
	/**
	 * @return the balance
	 */
	public long getCrAccNum() {
		return crAccNum;
	}
	/**
	 * @param balance the balance to set
	 */
	public void setCrAccNum(long crAccNum) {
		this.crAccNum = crAccNum;
	}
	public long getDrAccNum() {
		return crAccNum;
	}
	/**
	 * @param balance the balance to set
	 */
	public void setDrAccNum(long drAccNum) {
		this.drAccNum = drAccNum;
	}
	public long getAmount() {
		return amount;
	}
	/**
	 * @param balance the balance to set
	 */
	public void setAmount(long amount) {
		this.amount = amount;
	}
}
