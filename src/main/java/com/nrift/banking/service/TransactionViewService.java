package com.nrift.banking.service;

import java.sql.Connection;



import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import com.nrift.banking.dto.AccountDTO;
import com.nrift.banking.dto.TransactionHistoryDTO;
import com.nrift.banking.dto.TransactionViewDTO;
import com.nrift.banking.exception.BankingException;

public class TransactionViewService {

	public TransactionViewDTO getTransactionViewDetails(Connection connection,long accountNo) throws ServletException, SQLException, BankingException {
		
		//TransactionViewDetails transactionViewDetails= 
		AccountDTO accountDetails = new AccountService().getAccountDetails(connection,accountNo);
		
		TransactionViewDTO transactionViewDetails = new TransactionViewDTO(accountDetails.getAccountNo(), accountDetails.getAccountType(), accountDetails.getBalance());
		
		 transactionViewDetails.setTransactionHistoryDetailsList(new TransactionHistoryService().getTransactionHistoryDetails(connection,accountNo));
		 return transactionViewDetails;
	}

}


