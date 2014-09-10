package com.nrift.banking.utility;

import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;

public class TransactionHistoryManager {

	public List<TransactionHistoryDTO> getTransactionHistoryDetails(Connection connection,long accountNo) throws ServletException {
		AccountManager acc=new AccountManager();
		AccountDTO accountDetails= acc.getAccountDetails(connection, accountNo);
		return new TransactionHistoryDAO(connection).getTransactionHistoryDetails(accountNo) ;
	}


}