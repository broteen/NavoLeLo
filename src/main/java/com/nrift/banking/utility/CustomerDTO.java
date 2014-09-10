package com.nrift.banking.utility;

import java.io.Serializable;
import java.util.List;

/**
 * The Class Customer-Data Transfer Object.
 */
public class CustomerDTO implements Serializable {
    private static final long serialVersionUID = 6297385302078200512L;

    private long customerId;
    private String name;
    private long contactNumber;
    private String panNumber;
    private String email;
    private String address;
    private List<AccountDTO> accountList=null;

    /**
     * Instantiates a new customer dto.
     *
     * @param customerId the customer id
     * @param name the name
     * @param contactNumber the contact number
     * @param panNumber the pan number
     * @param email the email
     * @param address the address
     * @return the name
     */

    public CustomerDTO(long customerId,String name,long contactNumber, String panNumber,String email,String address) 
    {
        this.customerId=customerId;
        this.name=name;
        this.contactNumber=contactNumber;
        this.panNumber=panNumber;
        this.email=email;
        this.address=address;
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
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the contact number.
     *
     * @return the contactNumber
     */
    public long getContactNumber() {
        return contactNumber;
    }

    /**
     * Sets the contact number.
     *
     * @param contactNumber the contactNumber to set
     */
    public void setContactNumber(long contactNumber) {
        this.contactNumber = contactNumber;
    }

    /**
     * Gets the pan number.
     *
     * @return the panNumber
     */
    public String getPanNumber() {
        return panNumber;
    }

    /**
     * Sets the pan number.
     *
     * @param panNumber the panNumber to set
     */
    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    /**
     * Gets the email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email.
     *
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the address.
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address.
     *
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the account list.
     *
     * @return the accountList
     */
    public List<AccountDTO> getAccountList() {
        return accountList;
    }

    /**
     * Sets the account list.
     *
     * @param accountList the accountList to set
     */
    public void setAccountList(List<AccountDTO> accountList) {
        this.accountList = accountList;
    }


}
