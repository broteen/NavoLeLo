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
/*public long validateAccount(Connection connection, long account_number) throws ServletException {
		
	long customerId= new AccountDAO(connection).getCustomerId(account_number);
		
		return customerId;
		
	}

public CustomerDetails validateCustomer(Connection connection,long customerId) throws ServletException
{
	CustomerDetails customer=new CustomerDAO(connection).getCustomer_Details(customerId);
	return customer;
}
public boolean checkuser_ID(Connection connection,long customerId) throws ServletException
{
	boolean user_id=new CustomerDAO(connection).checkUserId(customerId);
	return user_id;
}*/


