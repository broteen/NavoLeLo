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

        Timestamp updatedTime= accountManager.getUpdateTime(connection, transAmtDetails.getSenderAccountNo());
        connection.setAutoCommit(false);
        if(updatedTime!=null && accountManager.IsAmountWithdrawn(connection,senderAccountNo,amount,updatedTime) && 
                accountManager.IsAmountDeposited(connection,receiverAccountNo,amount)){

            if(transaction.insertRowForTransferAmount(connection,senderAccountNo,receiverAccountNo,amount)!=0 && 
                    updatedTime.equals(accountManager.getUpdateTime(connection, transAmtDetails.getSenderAccountNo()))){

                if(accountManager.setUpdatedByandUpdatedTime(connection, transAmtDetails.getSenderAccountNo(),userId)){
                    connection.commit();
                    connection.setAutoCommit(true);
                    return true;
                }
            }else{
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }
        }
        return false;
    }
}
