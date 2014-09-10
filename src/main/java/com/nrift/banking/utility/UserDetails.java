package com.nrift.banking.utility;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * The Class UserDetails.
 */
public class UserDetails implements Serializable{

    private static final long serialVersionUID = 6297385302078200511L;

    private long userId;
    private String userName;
    private Timestamp lastLoggedOn;
    private boolean isAdmin;
    private CustomerDTO customerDetails=null;
    private AdminDTO AdminDetails=null;

    /**
     * Instantiates a new user details.
     *
     * @param userId the user id
     * @param userName the user name
     * @param isAdmin the is admin
     * @param lastLoggedOn the last logged on
     */
    public UserDetails(long userId, String userName,boolean isAdmin,Timestamp lastLoggedOn) {
        this.userId = userId;
        this.userName = userName;
        this.isAdmin = isAdmin;
        this.lastLoggedOn = lastLoggedOn;
    }

    /**
     * Gets the user id.
     *
     * @return the user id
     */
    public long getUserId() {
        return userId;
    }

    /**
     * Sets the user id.
     *
     * @param userId the new user id
     */
    public void setUserId(long userId) {
        this.userId = userId;
    }

    /**
     * Gets the user name.
     *
     * @return the user name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the user name.
     *
     * @param userName the new user name
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Gets the last logged on.
     *
     * @return the last logged on
     */
    public Timestamp getLastLoggedOn() {
        return lastLoggedOn;
    }

    /**
     * Sets the last logged on.
     *
     * @param lastLoggedOn the new last logged on
     */
    public void setLastLoggedOn(Timestamp lastLoggedOn) {
        this.lastLoggedOn = lastLoggedOn;
    }

    /**
     * Checks if is admin.
     *
     * @return true, if is admin
     */
    public boolean isAdmin() {
        return isAdmin;
    }

    /**
     * Sets the admin.
     *
     * @param isAdmin the new admin
     */
    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    /**
     * Gets the customer details.
     *
     * @return the customer details
     */
    public CustomerDTO getCustomerDetails() {
        return customerDetails;
    }

    /**
     * Sets the customer details.
     *
     * @param customerDetails the new customer details
     */
    public void setCustomerDetails(CustomerDTO customerDetails) {
        this.customerDetails = customerDetails;
    }

    /**
     * Gets the admin details.
     *
     * @return the admin details
     */
    public AdminDTO getAdminDetails() {
        return AdminDetails;
    }

    /**
     * Sets the admin details.
     *
     * @param adminDetails the new admin details
     */
    public void setAdminDetails(AdminDTO adminDetails) {
        AdminDetails = adminDetails;
    }

    /**
     * Gets the serial version uid.
     *
     * @return the serialversionuid
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }


}
