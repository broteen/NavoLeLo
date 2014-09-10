package com.nrift.banking.utility;

import java.sql.Connection;
import java.sql.SQLException;


public class AdminService {

	public AdminDTO getAdminDetails(Connection connection, long userId) throws SQLException{
		
		return (new AdminDAO(connection).getAdminDetails(userId));
		
	}

}
