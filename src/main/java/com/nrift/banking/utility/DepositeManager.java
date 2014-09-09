package com.nrift.banking.utility;

import java.sql.Connection;

import javax.servlet.ServletException;

public class DepositeManager {

	public long validateAccountNumber(Connection con,long accountNumber) {
		
		try{
		return new AccountManager().validateAccount(con,accountNumber);
		}
	
	catch(ServletException e)
	{
		//To be Implemented later this is not the correct implmentation
		return 0L;
	}
	}
	
	
	
}
