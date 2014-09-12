package com.nrift.banking.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.nrift.banking.dto.TransferAmountDTO;
import com.nrift.banking.exception.BankingException;


/**
 * The Class TransferAmountService.
 */
public class TransferAmountService {

    /**
     * Checks if is transfer successful.
     *
     * @param connection the connection
     * @param transAmtDetails the transfer amount details
     * @param userId the user id
     * @return true, if successful
     * @throws SQLException the SQL exception
     * @throws BankingException 
     */
    public void makeTransfer(Connection connection,TransferAmountDTO transAmtDetails,long userId) throws BankingException{
    	try{
	        AccountService accountManager= new AccountService();
	        TransactionService transaction= new TransactionService();
	        long senderAccountNo=transAmtDetails.getSenderAccountNo();
	        long receiverAccountNo=transAmtDetails.getReceiverAccountNo();
	        long amount=transAmtDetails.getAmount();
	
	        Timestamp senderUpdatedTime= accountManager.getUpdateTime(connection, transAmtDetails.getSenderAccountNo());
	        Timestamp receiverUpdatedTime= accountManager.getUpdateTime(connection, transAmtDetails.getReceiverAccountNo());
	        accountManager.withdrawAmount(connection,senderAccountNo,amount,senderUpdatedTime);
	        accountManager.depositAmount(connection,receiverAccountNo,amount,receiverUpdatedTime);
	        transaction.insertRowForTransferAmount(connection,senderAccountNo,receiverAccountNo,amount);
	        accountManager.setUpdatedByandUpdatedTime(connection, transAmtDetails.getSenderAccountNo(),userId);
	        connection.commit();
    	}catch(SQLException e){
    		throw new BankingException(e);
    	}
    }
}
