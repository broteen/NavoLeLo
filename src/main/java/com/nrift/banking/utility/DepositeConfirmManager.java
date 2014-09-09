package com.nrift.banking.utility;

import java.sql.Connection;

import javax.servlet.ServletException;

import com.nrift.banking.utility.TransactionManager;

public class DepositeConfirmManager {

	
	// UNDER CONFRIMDEPOSITEMANAGER
			public boolean IsDeposited(Connection connection,long receiverAccountNo, long amount)
			{
				try{
					return new AccountManager().IsAmountDeposited(connection,receiverAccountNo,amount);
				}
				
				catch(ServletException e)
				{
					return false;
				}
				
			}
			
			public boolean insertRowforDeposite(Connection connection,long senderAccountNo,long receiverAccountNo,long amount) throws ServletException{
				
				senderAccountNo=0L;
				int check=new TransactionManager().insertRowForTransferAmount(connection, senderAccountNo,receiverAccountNo,amount );
				if(check==0)
				{
					return false;
				}
				else
				{
					return true;
				}
				
			}
}
