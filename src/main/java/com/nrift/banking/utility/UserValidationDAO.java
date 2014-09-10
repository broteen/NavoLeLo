package com.nrift.banking.utility;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


import org.apache.log4j.Logger;

public class UserValidationDAO {
	private Connection connection;
	
	private Logger logger = Logger.getLogger(UserValidationDAO.class);

	public UserValidationDAO(Connection connection) {
		this.connection = connection;
	}
	
	private String getValidateQueryString() {
	        return "SELECT * FROM REGISTERED_USERS WHERE USER_NAME=? and PASSWORD=?";
	}
	
	private String getValidateUserNameQueryString() {
        return "SELECT * FROM REGISTERED_USERS WHERE USER_NAME=?";
	}
	
	
	public UserDetails validate(String username,String password) throws SQLException{
		UserDetails userDetails=null;
		ResultSet rs = null;
		try {
			rs = DBUtils.getResultSetFromSQL(connection, getValidateQueryString(), username,password);
			if (rs != null && rs.next()) {
				boolean isAdmin;
				if(rs.getString("IS_ADMIN").compareTo("n")==0)
					isAdmin=false;
				else
					isAdmin=true;
				userDetails = new UserDetails(rs.getLong("USER_ID"),username,isAdmin,rs.getTimestamp("LAST_LOGGED_ON"));
				logger.info("User found with details="+userDetails);
				
			}
		}finally{
			DBUtils.closeResultSet(rs);
        }
		return userDetails;
	}
	
	public boolean validateUserName(String username) throws SQLException{
		boolean user=false;
		ResultSet rs = null;
		
		try {
			rs = DBUtils.getResultSetFromSQL(connection, getValidateUserNameQueryString(), username);
			
			if (rs != null && rs.next()) {
				logger.info("User name already exists"+username);
				user=true;	
			}else
				user=false;
			}finally{
				DBUtils.closeResultSet(rs);
            }
           	return user;  
        }
}

