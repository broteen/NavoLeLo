package com.nrift.banking.utility;

import java.sql.Connection;
import java.sql.Timestamp;
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
	
	public Timestamp getUpdateTime(Connection connection,long accountNo) throws ServletException{
		return new AccountDAO(connection).getUpdatedTime(accountNo);
	}

public  AccountDetails getAccountHistory(Connection connection, long accountNo) throws ServletException {
	
	AccountDetails account= new AccountDAO(connection).getAccountDetails(accountNo);
	if(account!=null)
	{
	account.setTransactionHistoryDetailsList(new TransactionHistoryManager(). getTransactionHistoryDetails(connection,account.getAccountNo()));
	}
	return account;
}
public boolean IsAmountWithdrawn(Connection connection,long senderAccountNo, long amount) throws ServletException {
		if (new AccountDAO(connection).WithdrawAmount(senderAccountNo,amount)==0)
			return false;
		else
			return true;
	}

	public boolean IsAmountDeposited(Connection connection,long receiverAccountNo, long amount) throws ServletException {
		if (new AccountDAO(connection).DepositeAmount(receiverAccountNo,amount)==0)
			return false;
		else
			return true;
	}
}
