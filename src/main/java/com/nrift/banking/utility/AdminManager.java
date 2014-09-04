package com.nrift.banking.utility;

import java.sql.Connection;

import javax.servlet.ServletException;

public class AdminManager {

	public AdminDetails getAdminDetails(Connection connection, long userId) throws ServletException{
		
		return (new AdminDAO(connection).getAdminDetails(userId));
		
	}

}
