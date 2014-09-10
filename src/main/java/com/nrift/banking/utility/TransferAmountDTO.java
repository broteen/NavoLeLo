package com.nrift.banking.utility;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * The Class TransferAmountDTO.
 */
public class TransferAmountDTO  implements Serializable {
    private static final long serialVersionUID = 6297385302078200520L;	
    private long senderAccountNo;
    private long receiverAccountNo;
    private long amount;
    private Timestamp updatedTime;

    /**
     * Instantiates a new transfer amount dto.
     *
     * @param senderAccountNo the sender account no
     * @param receiverAccountNo the receiver account no
     * @param amount the amount
     * @param updatedTime the updated time
     */
    public TransferAmountDTO(long senderAccountNo,long receiverAccountNo,long amount,Timestamp updatedTime){
        this.senderAccountNo=senderAccountNo;
        this.receiverAccountNo=receiverAccountNo;
        this.amount=amount;
        this.updatedTime=updatedTime;
    }

    /**
     * Gets the sender account no.
     *
     * @return the sender account no
     */
    public long getSenderAccountNo() {
        return senderAccountNo;
    }

    /**
     * Sets the sender account no.
     *
     * @param senderAccountNo the new sender account no
     */
    public void setSenderAccountNo(long senderAccountNo) {
        this.senderAccountNo = senderAccountNo;
    }

    /**
     * Gets the receiver account no.
     *
     * @return the receiver account no
     */
    public long getReceiverAccountNo() {
        return receiverAccountNo;
    }

    /**
     * Sets the receiver account no.
     *
     * @param receiverAccountNo the new receiver account no
     */
    public void setReceiverAccountNo(long receiverAccountNo) {
        this.receiverAccountNo = receiverAccountNo;
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

    /**
     * Gets the updated time.
     *
     * @return the updated time
     */
    public Timestamp getUpdatedTime() {
        return updatedTime;
    }

    /**
     * Sets the updated time.
     *
     * @param updatedTime the new updated time
     */
    public void setUpdatedTime(Timestamp updatedTime) {
        this.updatedTime = updatedTime;
    }
}
