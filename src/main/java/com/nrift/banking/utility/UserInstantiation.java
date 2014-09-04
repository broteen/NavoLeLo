/**
 * 
 */
package com.nrift.banking.utility;

import java.sql.Connection;

import javax.servlet.ServletException;

/**
 * @author zeeshank
 *
 */
public class UserInstantiation {
	
	public static CustomerDetails getCustomerDetails(Connection connection, long userId) throws ServletException {
		
		return(new CustomerManager().getCustomerDetails(connection,userId));
		
	}
	
	public static AdminDetails getAdminDetails(Connection connection, long userId) throws ServletException {
		
		return(new AdminManager().getAdminDetails(connection,userId));
	}

}
