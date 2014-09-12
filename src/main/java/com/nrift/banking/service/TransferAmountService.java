package com.nrift.banking.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.nrift.banking.dto.TransferAmountDTO;


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
     */
    public boolean IsTransferSuccessfull(Connection connection,TransferAmountDTO transAmtDetails,long userId) throws SQLException {

        AccountService accountManager= new AccountService();
        TransactionService transaction= new TransactionService();
        long senderAccountNo=transAmtDetails.getSenderAccountNo();
        long receiverAccountNo=transAmtDetails.getReceiverAccountNo();
        long amount=transAmtDetails.getAmount();

        Timestamp senderUpdatedTime= accountManager.getUpdateTime(connection, transAmtDetails.getSenderAccountNo());
        Timestamp receiverUpdatedTime= accountManager.getUpdateTime(connection, transAmtDetails.getReceiverAccountNo());
        if(senderUpdatedTime!=null && accountManager.IsAmountWithdrawn(connection,senderAccountNo,amount,senderUpdatedTime) && 
                accountManager.IsAmountDeposited(connection,receiverAccountNo,amount,receiverUpdatedTime)){

            if(transaction.insertRowForTransferAmount(connection,senderAccountNo,receiverAccountNo,amount)!=0){

                if(accountManager.setUpdatedByandUpdatedTime(connection, transAmtDetails.getSenderAccountNo(),userId)){
                    connection.commit();
                    return true;
                }
            }
        }
        connection.rollback();         //See if we need to throw an exception here and remove con.rollback() from here
        return false;
    }
}
