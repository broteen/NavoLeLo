package com.nrift.banking.utility;

import java.sql.Timestamp;

/**
 * The Class WithdrawAmount-Data Transfer Object.
 */
public class WithdrawAmountDTO {
    private long accountNo;
    private long amount;
    private Timestamp updatedTime;

    /**
     * Instantiates a new withdraw amount dto.
     *
     * @param accountNo the account no
     * @param amount the amount
     * @param updatedTime the updated time
     */
    public WithdrawAmountDTO(long accountNo, long amount,
            Timestamp updatedTime) {
        this.accountNo = accountNo;
        this.amount = amount;
        this.updatedTime = updatedTime;
    }

    /**
     * Gets the account no.
     *
     * @return the account no
     */
    public long getAccountNo() {
        return accountNo;
    }

    /**
     * Sets the account no.
     *
     * @param accountNo the new account no
     */
    public void setAccountNo(long accountNo) {
        this.accountNo = accountNo;
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
