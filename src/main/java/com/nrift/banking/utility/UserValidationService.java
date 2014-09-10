package com.nrift.banking.utility;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;

public class UserValidationService {

	public UserDetails validate(Connection connection, String username,String password) throws SQLException {
		UserDetails user= new UserValidationDAO(connection).validate(username, password);
		
		if(user!=null)
		{
			if(user.isAdmin()== true)
			{
				user.setAdminDetails(UserInstantiation.getAdminDetails(connection, user.getUserId()));
			}
			else
			{
				user.setCustomerDetails(UserInstantiation.getCustomerDetails(connection, user.getUserId()));
			}
		}
		return user;
		
	}
	
	public boolean checkUserName(Connection connection, String username) throws SQLException
	{
		return(new UserValidationDAO(connection).validateUserName(username));
	}
	

}
