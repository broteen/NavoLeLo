package com.nrift.banking.utility;

import java.sql.Connection;

import javax.servlet.ServletException;

public class RegistrationManager {
	
	public long validateAccountNumber(Connection connection, long accountNumber)throws ServletException {
	
		return(new AccountManager().validateAccount(connection,accountNumber));
	}
	
	
	public CustomerDetails validateCustomerDetails(Connection connection,long customerId) throws ServletException
	{
		return(new CustomerManager().validateCustomer(connection,customerId));
	}
	
	public boolean checkuserID(Connection connection,long customerId) throws ServletException
	{
	  return(new CustomerManager().checkuser_ID(connection,customerId));
	}
}


