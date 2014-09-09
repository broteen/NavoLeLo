package com.nrift.banking.utility;

import java.sql.Timestamp;

public class WithdrawAmountDetails {
	private long AccountNo;
	private long amount;
	private Timestamp updatedTime;

	public WithdrawAmountDetails(long AccountNo, long amount,
			Timestamp updatedTime) {
		this.AccountNo = AccountNo;
		this.amount = amount;
		this.updatedTime = updatedTime;
	}

	public long getAccountNo() {
		return AccountNo;
	}

	public void setAccountNo(long senderAccountNo) {
		this.AccountNo = senderAccountNo;
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
