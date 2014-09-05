package com.nrift.banking.utility;

import java.sql.Connection;

public class TransferAuthorizationManager {
	public AccountDetails validate(Connection connection, String receiverAccount) {
		/*
		long customerId= new CustomerManager().getCustomerId(connection,receiverAccount);
		if(customerId==0L)
			return null;
		
		return (new AccountManager().getAccountDetails(connection, receiverAccount));
		*/
		return null;
	}

}
