package com.nrift.banking.utility;

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

    /**
     * Gets the transaction ref.
     *
     * @return the accountNo
     */
    public long getTransactionRef() {
        return transactionRef;
    }

    /**
     * Sets the transaction refo.
     *
     * @param transactionRef the new transaction refo
     */
    public void setTransactionRefo(long transactionRef ) {
        this.transactionRef = transactionRef;
    }

    /**
     * Gets the time stamp.
     *
     * @return the accountType
     */
    public Timestamp getTimeStamp() {
        return transactionTime;
    }

    /**
     * Sets the time stamp.
     *
     * @param timeStamp the new time stamp
     */
    public void setTimeStamp(int timeStamp) {
        this.transactionTime=transactionTime;
    }

    /**
     * Gets the cr acc num.
     *
     * @return the balance
     */
    public long getCrAccNum() {
        return crAccNum;
    }

    /**
     * Sets the cr acc num.
     *
     * @param crAccNum the new cr acc num
     */
    public void setCrAccNum(long crAccNum) {
        this.crAccNum = crAccNum;
    }

    /**
     * Gets the dr acc num.
     *
     * @return the dr acc num
     */
    public long getDrAccNum() {
        return crAccNum;
    }

    /**
     * Sets the dr acc num.
     *
     * @param drAccNum the new dr acc num
     */
    public void setDrAccNum(long drAccNum) {
        this.drAccNum = drAccNum;
    }

    /**
     * Gets the amount.
     *
     * @return the amount
     */
    public long getAmount() {
        return amount;
    }

    /**
     * Sets the amount.
     *
     * @param amount the new amount
     */
    public void setAmount(long amount) {
        this.amount = amount;
    }
}
