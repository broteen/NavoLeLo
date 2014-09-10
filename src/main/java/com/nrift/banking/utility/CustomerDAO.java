package com.nrift.banking.utility;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


import org.apache.log4j.Logger;

public class CustomerDAO {
	
	private Connection connection;
	
	private Logger logger = Logger.getLogger(CustomerDAO.class);

	public CustomerDAO(Connection connection) {
		this.connection = connection;
	}
	
	private String getCustomerByUserIdQueryString() {
        return "SELECT * FROM CUSTOMER WHERE USER_ID=?";
    }
	
	private String getCustomerByCustomerIdQueryString() {
        return "SELECT * FROM CUSTOMER WHERE CUSTOMER_ID=?";
    }
	
	private String getCheckUserIdString() {
        return "SELECT USER_ID FROM CUSTOMER WHERE CUSTOMER_ID=?";
    }
	
	
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
