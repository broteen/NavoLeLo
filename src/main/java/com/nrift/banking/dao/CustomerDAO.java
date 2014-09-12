package com.nrift.banking.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.nrift.banking.dto.CustomerDTO;
import com.nrift.banking.exception.BankingException;
import com.nrift.banking.utility.DBHelper;

// TODO: Auto-generated Javadoc
/**
 * The Class Customer-Data Access Object.
 */
public class CustomerDAO {

    /** The connection. */
    private Connection connection;

    /** The logger. */
    private Logger logger = Logger.getLogger(CustomerDAO.class);

    /**
     * Instantiates a new customer dao.
     *
     * @param connection the connection
     */
    public CustomerDAO(Connection connection) {
        this.connection = connection;
    }



    /** The Constant CUSTOMER_BY_USER_ID_QUERY_STRING. */
    private static final String CUSTOMER_BY_USER_ID_QUERY_STRING= "select * from customer where user_id=?";


    /** The Constant CUSTOMER_BY_CUSTOMER_ID_QUERY_STRING. */
    private static final String CUSTOMER_BY_CUSTOMER_ID_QUERY_STRING ="select * from customer where customer_id=?";


    /** The Constant CHECK_USER_ID_QUERY_STRING. */
    private static final String CHECK_USER_ID_QUERY_STRING="select user_id from customer where customer_id=?";

    /**
     * Gets the customer details by user id.
     *
     * @param userId the user id
     * @return the customer details by user id
     * @throws SQLException the SQL exception
     * @throws BankingException 
     */
    public CustomerDTO getCustomerDetailsByUserId(long userId) throws SQLException, BankingException {

        ResultSet rs = null;
        try {
            rs = DBHelper.getResultSetFromSQL(connection, CUSTOMER_BY_USER_ID_QUERY_STRING, userId);
            if (rs != null && rs.next()) 
            {
            	CustomerDTO customerDetails = new CustomerDTO(rs.getLong("CUSTOMER_ID"),rs.getString("NAME"),rs.getLong("CONTACT_NUMBER"),rs.getString("PAN_NUMBER"),rs.getString("EMAIL"),rs.getString("ADDRESS"));
                logger.info("Customer found with details="+customerDetails);
                return customerDetails;
        	}
        	throw new BankingException("Customer Details is Empty");
        }finally{
        	DBHelper.closeResultSet(rs);
        }
    }

    /**
     * Gets the customer details by customer id.
     *
     * @param customerId the customer id
     * @return the customer details by customer id
     * @throws SQLException the SQL exception
     * @throws BankingException 
     */
    public CustomerDTO getCustomerDetailsByCustomerId(long customerId) throws SQLException, BankingException {

        CustomerDTO customerDetails=null;
        ResultSet rs = null;
        try {
            rs = DBHelper.getResultSetFromSQL(connection, CUSTOMER_BY_CUSTOMER_ID_QUERY_STRING, customerId);
            if (rs != null && rs.next()) 
            {
                customerDetails = new CustomerDTO(rs.getLong("CUSTOMER_ID"),rs.getString("NAME"),rs.getLong("CONTACT_NUMBER"),rs.getString("PAN_NUMBER"),rs.getString("EMAIL"),rs.getString("ADDRESS"));
                logger.info("Customer found with details="+customerDetails);
                return customerDetails;
        	}
        	throw new BankingException("Customer Details is Empty");
        }finally{
        	DBHelper.closeResultSet(rs);
        }

    }

    /**
     * Check user id.
     *
     * @param customerId the customer id
     * @return 
     * @return true, if successful
     * @throws SQLException the SQL exception
     */
    public boolean checkUserId(long customerId) throws SQLException {        //Couldn't understand this method kindly change it.
        boolean result=false;
        ResultSet rs = null;
        try {
            rs = DBHelper.getResultSetFromSQL(connection, CHECK_USER_ID_QUERY_STRING, customerId);
            if (rs != null && rs.next()) 
            {
               long userId = rs.getLong("USER_ID");
                if(userId==0){
                    logger.info("new customer please go to log info page");
                result=true;
                }
            }
        }finally{
            DBHelper.closeResultSet(rs);       
        }
        return result;
    }		

}
