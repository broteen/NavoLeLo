package com.nrift.banking.utility;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;

public class RegistrationService {
	
	public long validateAccountNumber(Connection connection, long accountNumber)throws SQLException {
	
		return(new AccountService().validateAccount(connection,accountNumber));
	}
	
	
	public CustomerDTO validateCustomerDetails(Connection connection,long customerId) throws SQLException
	{
		return(new CustomerService().validateCustomer(connection,customerId));
	}
	
	public boolean checkuserID(Connection connection,long customerId) throws SQLException
	{
	  return(new CustomerService().checkuser_ID(connection,customerId));
	}
}


