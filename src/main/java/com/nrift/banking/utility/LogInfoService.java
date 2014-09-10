package com.nrift.banking.utility;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;

public class LogInfoService {
	
	
	public boolean validateUsername(Connection connection, String username) throws SQLException{
		return(new UserValidationService().checkUserName(connection, username));

}

}