package com.nrift.banking.utility;

import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;

public class AccountManager {

	public List<AccountDetails> getAccountDetails(Connection connection,long customerId) throws ServletException {
		
		return(new AccountDAO(connection).getAccountDetails(customerId) );
	}

}
