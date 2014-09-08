package com.nrift.banking.utility;

import java.sql.Connection;

import javax.servlet.ServletException;

public class TransferAuthorizationManager {
	public TransferAmountDetails validate(Connection connection,long senderAccount,long receiverAccount,long amount) throws ServletException {
		AccountManager acc=new AccountManager();
		AccountDetails senderAccountDetails= acc.getAccountDetails(connection, senderAccount);
		AccountDetails receiverAccountDetails= acc.getAccountDetails(connection, receiverAccount);
		if(senderAccountDetails==null || receiverAccountDetails==null)
			return null;
		if(senderAccountDetails.getBalance() > amount)
			return new TransferAmountDetails(senderAccount,receiverAccount,amount,senderAccountDetails.getUpdatedTime());
		else
			return null;
	}

}
