package com.nrift.banking.utility;

import java.sql.Connection;

import javax.servlet.ServletException;

public class LogInfoManager {
	
	
	public boolean validateUsername(Connection connection, String username) throws ServletException{
		return(new UserValidationManager().checkUserName(connection, username));

}

}