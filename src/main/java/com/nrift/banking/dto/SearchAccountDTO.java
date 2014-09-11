package com.nrift.banking.dto;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * The Class SearchAccount-Data Transfer Object.
 */
public class SearchAccountDTO  implements Serializable {
    private static final long serialVersionUID = 6297385302078200520L;	
    private String customerName;
    private long customerId;
    private long amount;
    private long panNumber;

    /**
     * Instantiates a new search account dto.
     *
     * @param customerName the customer name
     * @param customerId the customer id
     * @param amount the amount
     * @param panNumber the pan number
     */
    public SearchAccountDTO(String customerName,long customerId,long amount,long panNumber){
        this.customerName=customerName;
        this.customerId=customerId;
        this.amount=amount;
        this.panNumber=panNumber;
    }

    /**
     * Gets the customer name.
     *
     * @return the customer name
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Sets the customer name.
     *
     * @param customerName the new customer name
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * Gets the customer id.
     *
     * @return the customer id
     */
    public long getCustomerId() {
        return customerId;
    }

    /**
     * Sets the customer id.
     *
     * @param customerId the new customer id
     */
    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    /**
     * Gets the amount.
     *
     * @param amount the amount
     * @return the amount
     */
    public long getAmount(long amount) {
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
     * Gets the pan number.
     *
     * @param panNumber the pan number
     * @return the pan number
     */
    public long getPanNumber(long panNumber) {
        return panNumber;
    }

    /**
     * Sets the pan n umber.
     *
     * @param panNumber the new pan n umber
     */
    public void setPanNUmber(long panNumber) {
        this.panNumber = panNumber;
    }
}
