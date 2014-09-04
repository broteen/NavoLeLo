package com.nrift.banking.utility;

import java.sql.Connection;

import javax.servlet.ServletException;

public class UserValidationManager {

	public UserDetails validate(Connection connection, String username,String password) throws ServletException {
		
		UserValidationDAO userValidation= new UserValidationDAO(connection, username, password);
		UserDetails user= userValidation.validate();
		
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
	
	

}
