package com.nrift.banking.utility;

import java.sql.Connection;
import java.sql.Timestamp;

import javax.servlet.ServletException;

public class TransferAmount {

	public boolean IsTransferSuccessfull(Connection connection,TransferAmountDetails transferAmountDetails) throws ServletException {
	
		try{
		AccountManager accountManager= new AccountManager();
		Timestamp updatedTime= accountManager.getUpdateTime(connection, transferAmountDetails.getSenderAccountNo());
		//if(accountManager.Update)
		}catch(Exception e){
			
		}
		
	}
}
