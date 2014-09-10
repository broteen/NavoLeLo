package com.nrift.banking.utility;

import java.sql.Connection;

import javax.servlet.ServletException;

public class TransferAuthorizationManager {
	public TransferAmountDTO validate(Connection connection,long senderAccount,long receiverAccount,long amount) throws ServletException {
		AccountManager acc=new AccountManager();
		AccountDTO senderAccountDetails= acc.getAccountDetails(connection, senderAccount);
		AccountDTO receiverAccountDetails= acc.getAccountDetails(connection, receiverAccount);
		if(senderAccountDetails==null || receiverAccountDetails==null)
			return null;
		if(senderAccountDetails.getBalance() > amount)
			return new TransferAmountDTO(senderAccount,receiverAccount,amount,senderAccountDetails.getUpdatedTime());
		else
			return null;
	}

}
