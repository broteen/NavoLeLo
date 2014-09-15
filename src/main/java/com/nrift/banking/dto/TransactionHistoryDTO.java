package com.nrift.banking.dto;

import java.io.Serializable;

import java.sql.Timestamp;

// TODO: Auto-generated Javadoc
/**
 * The Class TransactionHistory-Data Transfer Object.
 */
public class TransactionHistoryDTO implements Serializable{

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 6297385302078200515L;

    /** The transaction ref. */
    private long transactionRef;

    /** The cr acc num. */
    private long crAccNum;

    /** The dr acc num. */
    private long drAccNum;

    /** The amount. */
    private long amount;

    /** The transaction time. */
    private Timestamp transactionTime;

    /**
     * Instantiates a new transaction history dto.
     *
     * @param transactionRef the transaction ref
     * @param transactionTime the transaction time
     * @param crAccNum the cr acc num
     * @param drAccNum the dr acc num
     * @param amount the amount
     */
    public TransactionHistoryDTO (long transactionRef, Timestamp transactionTime, long crAccNum, long drAccNum, long amount) {

        this.transactionRef=transactionRef;
        this.transactionTime=transactionTime;
        this.crAccNum=crAccNum;
        this.drAccNum=drAccNum;
        this.amount=amount;
    }

	public long getTransactionRef() {
		return transactionRef;
	}

	public void setTransactionRef(long transactionRef) {
		this.transactionRef = transactionRef;
	}

	public long getCrAccNum() {
		return crAccNum;
	}

	public void setCrAccNum(long crAccNum) {
		this.crAccNum = crAccNum;
	}

	public long getDrAccNum() {
		return drAccNum;
	}

	public void setDrAccNum(long drAccNum) {
		this.drAccNum = drAccNum;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public Timestamp getTransactionTime() {
		return transactionTime;
	}

	public void setTransactionTime(Timestamp transactionTime) {
		this.transactionTime = transactionTime;
	}
}

