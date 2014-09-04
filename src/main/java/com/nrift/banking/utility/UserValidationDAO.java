package com.nrift.banking.utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;

public class UserValidationDAO {
	private Connection connection;
	private String username;
	private String password;
	
	private Logger logger = Logger.getLogger(UserValidationDAO.class);

	public UserValidationDAO(Connection connection, String username,
			String password) {
		this.connection = connection;
		this.username = username;
		this.password = password;
	}

	public UserDetails validate() throws ServletException{
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement("SELECT * FROM REGISTERED_USERS WHERE USER_NAME=? and PASSWORD=?");

			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if (rs != null && rs.next()) 
			{
				boolean isAdmin;
				if(rs.getString("IS_ADMIN").compareTo("n")==0)
				{
					isAdmin=false;
				}
				else
				{
					isAdmin=true;
				}
				UserDetails validUser = new UserDetails(rs.getLong("USER_ID"),username,isAdmin,rs.getTimestamp("LAST_LOGGED_ON"));
				logger.info("User found with details="+validUser);
				return validUser;
			}
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("SQLException in exracting data from the ResultSet");
			throw new ServletException("DB Connection problem.");
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
