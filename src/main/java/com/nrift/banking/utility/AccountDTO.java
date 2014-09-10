package com.nrift.banking.utility;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * The Class Account-Data Transfer Object.
 */
public class AccountDTO implements Serializable{
    private static final long serialVersionUID = 6297385302078200515L;

    private long accountNo;
    private String accountType;
    private long balance;
    private Timestamp updatedTime;
    private List<TransactionHistoryDTO> transactionHistoryDetailsList=null;

    /**
     * Instantiates a new account dto.
     *
     * @param accountNo the account no
     * @param accountType the account type
     * @param balance the balance
     * @param updatedTime the updated time
     */
    public AccountDTO(long accountNo, String accountType, long balance, Timestamp updatedTime) {

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

    /**
     * Gets the transaction history details list.
     *
     * @return the transaction history details list
     */
    public List<TransactionHistoryDTO> getTransactionHistoryDetailsList() {
        return transactionHistoryDetailsList;
    }

    /**
     * @param accountList the accountList to set
     */
    public void setTransactionHistoryDetailsList(List<TransactionHistoryDTO> list) {
        this.transactionHistoryDetailsList = transactionHistoryDetailsList;
    }


}



