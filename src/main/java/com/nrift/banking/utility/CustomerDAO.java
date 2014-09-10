package com.nrift.banking.utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;

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
     * Gets the customer details.
     *
     * @param userId the user id
     * @return the customer details
     * @throws ServletException the servlet exception
     */
    public CustomerDTO getCustomerDetails(long userId) throws ServletException{

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement("SELECT * FROM CUSTOMER WHERE USER_ID=?");

            ps.setLong(1, userId);
            rs = ps.executeQuery();
            if (rs != null && rs.next()) 
            {
                CustomerDTO validCustomer = new CustomerDTO(rs.getLong("CUSTOMER_ID"),rs.getString("NAME"),rs.getLong("CONTACT_NUMBER"),rs.getString("PAN_NUMBER"),rs.getString("EMAIL"),rs.getString("ADDRESS"));
                logger.info("Customer found with details="+validCustomer);
                return validCustomer;
            }
            return null;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            logger.error("SQLException in exracting data from the ResultSet in CustomerDAO.");
            throw new ServletException("DB Connection problem in CustomerDAO.");
        }finally{
            try {
                rs.close();
                ps.close();
            } catch (SQLException e) {
                logger.error("SQLException in closing PreparedStatement or ResultSet");
            }

        }

    }
    
    /**
     * Gets the customer_ details.
     *
     * @param customer_ID the customer_ id
     * @return the customer_ details
     * @throws ServletException the servlet exception
     */
    public CustomerDTO getCustomer_Details(long customer_ID) throws ServletException{

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement("SELECT * FROM CUSTOMER WHERE CUSTOMER_ID=?");

            ps.setLong(1,customer_ID);
            rs = ps.executeQuery();
            if (rs != null && rs.next()) 
            {
                CustomerDTO validCustomer = new CustomerDTO(rs.getLong("CUSTOMER_ID"),rs.getString("NAME"),rs.getLong("CONTACT_NUMBER"),rs.getString("PAN_NUMBER"),rs.getString("EMAIL"),rs.getString("ADDRESS"));
                logger.info("Customer found with details="+validCustomer);
                return validCustomer;
            }
            return null;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            logger.error("SQLException in exracting data from the ResultSet in CustomerDAO.");
            throw new ServletException("DB Connection problem in CustomerDAO.");
        }
        finally{
            try {
                rs.close();
                ps.close();
            } catch (SQLException e) {
                logger.error("SQLException in closing PreparedStatement or ResultSet");
            }

        }

    }
    
    /**
     * Check user id.
     *
     * @param customerID the customer id
     * @return true, if successful
     * @throws ServletException the servlet exception
     */
    public boolean checkUserId(long customerID) throws ServletException
    {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement("SELECT USER_ID FROM CUSTOMER WHERE CUSTOMER_ID=?");

            ps.setLong(1, customerID);
            rs = ps.executeQuery();
            if (rs != null) 
            {
                String user_ID = rs.getString("USER_ID");
                if(user_ID.equals("NULL"))
                    logger.info("new customer please go to log info page");
                return true;
            }
            return false;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            logger.error("SQLException in exracting data from the ResultSet in CustomerDAO.");
            throw new ServletException("DB Connection problem in CustomerDAO.");
        }
        finally{
            try {
                rs.close();
                ps.close();
            } catch (SQLException e) {
                logger.error("SQLException in closing PreparedStatement or ResultSet");
            }

        }
    }		

}
