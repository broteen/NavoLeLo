package com.nrift.banking.utility;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;

public class CustomerService {
	
public  CustomerDTO getCustomerDetails(Connection connection, long userId) throws SQLException {
		
			CustomerDTO customer= new CustomerDAO(connection).getCustomerDetailsByUserId(userId);
			if(customer!=null)
			{
			customer.setAccountList(new AccountService().getAllAccountDetails(connection,customer.getCustomerId()));
			}
			return customer;
	}

public CustomerDTO validateCustomer(Connection connection,long customerId) throws SQLException
{
	CustomerDTO customer=new CustomerDAO(connection).getCustomerDetailsByCustomerId(customerId);
	return customer;
}
public boolean checkuser_ID(Connection connection,long customerId) throws SQLException
{
	boolean user_id=new CustomerDAO(connection).checkUserId(customerId);
	return user_id;
}

}