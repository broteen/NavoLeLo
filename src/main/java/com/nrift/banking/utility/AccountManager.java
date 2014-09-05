package com.nrift.banking.utility;

import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;

public class AccountManager {

	public List<AccountDetails> getAllAccountDetails(Connection connection,long customerId) throws ServletException {
		
		return(new AccountDAO(connection).getAllAccountDetails(customerId) );
	}
	
	public AccountDetails getAccountDetails(Connection connection,String receiverAccount) throws ServletException {
		
		return(new AccountDAO(connection).getAccountDetails(receiverAccount) );
	}
}
