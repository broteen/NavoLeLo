package com.nrift.banking.utility;

import java.sql.Connection;

import javax.servlet.ServletException;

public class AdminManager {

	public AdminDTO getAdminDetails(Connection connection, long userId) throws ServletException{
		
		return (new AdminDAO(connection).getAdminDetails(userId));
		
	}

}
