package com.nrift.banking.utility;

import java.sql.Connection;
import java.util.List;




import javax.servlet.ServletException;

public class AccountManager {

	public List<AccountDetails> getAllAccountDetails(Connection connection,long customerId) throws ServletException {
		
		return new AccountDAO(connection).getAllAccountDetails(customerId);
	}
	
	public long validateAccount(Connection connection, long account_number) throws ServletException {
		
		long customerId= new AccountDAO(connection).getCustomerId(account_number);
			
			return customerId;
			
		}

	public AccountDetails getAccountDetails(Connection connection,long receiverAccount) throws ServletException {
		
		return new AccountDAO(connection).getAccountDetails(receiverAccount);
	}

public  AccountDetails getAccountHistory(Connection connection, long accountNo) throws ServletException {
	
	AccountDetails account= new AccountDAO(connection).getAccountDetails(accountNo);
	if(account!=null)
	{
	account.setTransactionHistoryDetailsList(new TransactionHistoryManager(). getTransactionHistoryDetails(connection,account.getAccountNo()));
	}
	return account;
}
}
