package com.nrift.banking.utility;

import java.sql.Timestamp;

public class TransferAmountDetails {
	private long senderAccountNo;
	private long receiverAccountNo;
	private long amount;
	private Timestamp updatedTime;
	public TransferAmountDetails(long senderAccountNo,long receiverAccountNo,long amount,Timestamp updatedTime){
		this.senderAccountNo=senderAccountNo;
		this.receiverAccountNo=receiverAccountNo;
		this.amount=amount;
		this.updatedTime=updatedTime;
	}
	public long getSenderAccountNo() {
		return senderAccountNo;
	}
	public void setSenderAccountNo(long senderAccountNo) {
		this.senderAccountNo = senderAccountNo;
	}
	public long getReceiverAccountNo() {
		return receiverAccountNo;
	}
	public void setReceiverAccountNo(long receiverAccountNo) {
		this.receiverAccountNo = receiverAccountNo;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	public Timestamp getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Timestamp updatedTime) {
		this.updatedTime = updatedTime;
	}
}
