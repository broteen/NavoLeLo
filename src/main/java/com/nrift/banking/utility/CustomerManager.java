package com.nrift.banking.utility;

import java.sql.Connection;

import javax.servlet.ServletException;

public class CustomerManager {
	
public  CustomerDetails getCustomerDetails(Connection connection, long userId) throws ServletException {
		
			CustomerDetails customer= new CustomerDAO(connection).getCustomerDetails(userId);
			if(customer!=null)
			{
			customer.setAccountList(new AccountManager().getAllAccountDetails(connection,customer.getCustomerId()));
			}
			return customer;
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
}

}