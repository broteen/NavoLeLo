package com.nrift.banking.utility;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


import org.apache.log4j.Logger;

/**
 * The Class CustomerDAO.
 */
public class CustomerDAO {

    private Connection connection;

    private Logger logger = Logger.getLogger(CustomerDAO.class);

    /**
     * Instantiates a new customer dao.
     *
     * @param connection the connection
     */
    public CustomerDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     * Gets the customer by user id query string.
     *
     * @return the customer by user id query string
     */
    private String getCustomerByUserIdQueryString() {
        return "SELECT * FROM CUSTOMER WHERE USER_ID=?";
    }

    /**
     * Gets the customer by customer id query string.
     *
     * @return the customer by customer id query string
     */
    private String getCustomerByCustomerIdQueryString() {
        return "SELECT * FROM CUSTOMER WHERE CUSTOMER_ID=?";
    }

    /**
     * Gets the check user id string.
     *
     * @return the check user id string
     */
    private String getCheckUserIdString() {
        return "SELECT USER_ID FROM CUSTOMER WHERE CUSTOMER_ID=?";
    }


    /**
     * Gets the customer details by user id.
     *
     * @param userId the user id
     * @return the customer details by user id
     * @throws SQLException the SQL exception
     */
    public CustomerDTO getCustomerDetailsByUserId(long userId) throws SQLException {

        CustomerDTO customerDetails=null;
        ResultSet rs = null;
        try {
            rs = DBUtils.getResultSetFromSQL(connection, getCustomerByUserIdQueryString(), userId);
            if (rs != null && rs.next()) 
            {
                customerDetails = new CustomerDTO(rs.getLong("CUSTOMER_ID"),rs.getString("NAME"),rs.getLong("CONTACT_NUMBER"),rs.getString("PAN_NUMBER"),rs.getString("EMAIL"),rs.getString("ADDRESS"));
                logger.info("Customer found with details="+customerDetails);
            }
        }finally{
            DBUtils.closeResultSet(rs);  
        }
        return customerDetails;
    }

    /**
     * Gets the customer details by customer id.
     *
     * @param customerId the customer id
     * @return the customer details by customer id
     * @throws SQLException the SQL exception
     */
    public CustomerDTO getCustomerDetailsByCustomerId(long customerId) throws SQLException {

        CustomerDTO customerDetails=null;
        ResultSet rs = null;
        try {
            rs = DBUtils.getResultSetFromSQL(connection, getCustomerByCustomerIdQueryString(), customerId);
            if (rs != null && rs.next()) 
            {
                customerDetails = new CustomerDTO(rs.getLong("CUSTOMER_ID"),rs.getString("NAME"),rs.getLong("CONTACT_NUMBER"),rs.getString("PAN_NUMBER"),rs.getString("EMAIL"),rs.getString("ADDRESS"));
                logger.info("Customer found with details="+customerDetails);
            }
        }finally{
            DBUtils.closeResultSet(rs);  
        }
        return customerDetails;

    }

    /**
     * Check user id.
     *
     * @param customerId the customer id
     * @return true, if successful
     * @throws SQLException the SQL exception
     */
    public boolean checkUserId(long customerId) throws SQLException {
        boolean result=false;
        ResultSet rs = null;
        try {
            rs = DBUtils.getResultSetFromSQL(connection, getCheckUserIdString(), customerId);
            if (rs != null && rs.next()) 
            {
                String userId = rs.getString("USER_ID");
                if(userId==null)
                    logger.info("new customer please go to log info page");
                result=true;
            }
        }finally{
            DBUtils.closeResultSet(rs);       
        }
        return result;
    }		

}
