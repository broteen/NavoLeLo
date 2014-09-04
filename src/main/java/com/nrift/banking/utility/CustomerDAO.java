package com.nrift.banking.utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;

public class CustomerDAO {
	
	private Connection connection;
	
	private Logger logger = Logger.getLogger(CustomerDAO.class);

	public CustomerDAO(Connection connection) {
		this.connection = connection;
	}

	public CustomerDetails getCustomerDetails(long userId) throws ServletException{
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement("SELECT * FROM CUSTOMER WHERE USER_ID=?");

			ps.setLong(1, userId);
			rs = ps.executeQuery();
			if (rs != null && rs.next()) 
			{
				CustomerDetails validCustomer = new CustomerDetails(rs.getLong("CUSTOMER_ID"),rs.getString("NAME"),rs.getLong("CONTACT_NUMBER"),rs.getString("PAN_NUMBER"),rs.getString("EMAIL"),rs.getString("ADDRESS"));
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

}
