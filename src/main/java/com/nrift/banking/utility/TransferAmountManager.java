package com.nrift.banking.utility;

import java.sql.Connection;
import java.sql.Timestamp;

import javax.servlet.ServletException;

public class TransferAmountManager {

	public boolean IsTransferSuccessfull(Connection connection,TransferAmountDetails transAmtDetails,long userId) throws ServletException {
	
		try{
		AccountManager accountManager= new AccountManager();
		TransactionManager transaction= new TransactionManager();
		long senderAccountNo=transAmtDetails.getSenderAccountNo();
		long receiverAccountNo=transAmtDetails.getReceiverAccountNo();
		long amount=transAmtDetails.getAmount();
		
		Timestamp updatedTime= accountManager.getUpdateTime(connection, transAmtDetails.getSenderAccountNo());
		connection.setAutoCommit(false);
		if(updatedTime!=null && accountManager.IsAmountWithdrawn(connection,senderAccountNo,amount) && accountManager.IsAmountDeposited(connection,receiverAccountNo,amount)){
			
			if(transaction.insertRowForTransferAmount(connection,senderAccountNo,receiverAccountNo,amount)!=0 && 
					updatedTime.equals(accountManager.getUpdateTime(connection, transAmtDetails.getSenderAccountNo()))){
				
				if(accountManager.setUpdatedByandUpdatedTime(connection, transAmtDetails.getSenderAccountNo(),userId)){
				connection.commit();
				connection.setAutoCommit(true);
				return true;
				}
			}
			else
			{
				connection.rollback();
				connection.setAutoCommit(true);
				return false;
			}
		}
		}catch(Exception e){
			System.out.println(e.getStackTrace());
		}
		return false;
	}
}
