package com.nrift.banking.utility;

import java.sql.Timestamp;

public class WithdrawAmountDTO {
	private long accountNo;
	private long amount;
	private Timestamp updatedTime;

	public WithdrawAmountDTO(long accountNo, long amount,
			Timestamp updatedTime) {
		this.accountNo = accountNo;
		this.amount = amount;
		this.updatedTime = updatedTime;
	}

	public long getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
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
