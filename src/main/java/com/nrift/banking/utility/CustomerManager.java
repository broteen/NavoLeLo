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

}
