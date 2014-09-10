package com.nrift.banking.utility;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.ServletException;

import com.nrift.banking.utility.TransactionService;

public class DepositeConfirmService {
	
			public boolean IsDeposited(Connection connection,long receiverAccountNo, long amount, long userId) throws SQLException
			{
				try{
					AccountService accountManager= new AccountService();
					TransactionService transaction= new TransactionService();
					Timestamp updatedTime= accountManager.getUpdateTime(connection, receiverAccountNo);
					
					connection.setAutoCommit(false);
					if(updatedTime!=null && accountManager.IsAmountDeposited(connection,receiverAccountNo,amount)){
						if(transaction.insertRowForTransferAmount(connection,0L,receiverAccountNo,amount)!=0 && 
								updatedTime.equals(accountManager.getUpdateTime(connection, receiverAccountNo))){
							
							if(accountManager.setUpdatedByandUpdatedTime(connection,receiverAccountNo, userId)){
							connection.commit();
							connection.setAutoCommit(true);
							return true;
							}
				          }
					}
					else
					{
						connection.rollback();
						connection.setAutoCommit(true);
						return false;
					}
				}catch(ServletException e)
				{
					return false;
				}
				return false;
				
			}
	
		}

