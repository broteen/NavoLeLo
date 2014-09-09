package com.nrift.banking.utility;

import java.sql.Connection;
import java.sql.Timestamp;

public class withdrawAmountManager {

	public boolean IsWithdrawSuccessfull(Connection connection,
			WithdrawAmountDetails withdrawAmountDetails) {
		// TODO Auto-generated method stub
		try{
		AccountManager accountManager= new AccountManager();
		TransactionManager transaction= new TransactionManager();
		long AccountNo=withdrawAmountDetails.getAccountNo();
		long amount=withdrawAmountDetails.getAmount();
		
		Timestamp updatedTime= accountManager.getUpdateTime(connection, withdrawAmountDetails.getAccountNo());
		if(updatedTime!=null && accountManager.IsAmountWithdrawn(connection,AccountNo,amount)){
			
			if(transaction.insertRowForWithdrawAmount(connection,AccountNo,amount)!=0)
				return true;
			else
				return false;
		}
		}catch(Exception e){
			System.out.println(e.getStackTrace());
		}
		return false;
	}

}
