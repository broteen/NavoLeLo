package com.nrift.banking.utility;

import java.io.Serializable;
import java.util.List;

/**
 * The Class CustomerDTO.
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
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the contactNumber
     */
    public long getContactNumber() {
        return contactNumber;
    }

    /**
     * @param contactNumber the contactNumber to set
     */
    public void setContactNumber(long contactNumber) {
        this.contactNumber = contactNumber;
    }

    /**
     * @return the panNumber
     */
    public String getPanNumber() {
        return panNumber;
    }

    /**
     * @param panNumber the panNumber to set
     */
    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the accountList
     */
    public List<AccountDTO> getAccountList() {
        return accountList;
    }

    /**
     * @param accountList the accountList to set
     */
    public void setAccountList(List<AccountDTO> accountList) {
        this.accountList = accountList;
    }
}
