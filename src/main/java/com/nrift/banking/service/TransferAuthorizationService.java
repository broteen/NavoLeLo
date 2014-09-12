package com.nrift.banking.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;

import com.nrift.banking.dto.AccountDTO;
import com.nrift.banking.dto.TransferAmountDTO;
import com.nrift.banking.exception.BankingException;

/**
 * The Class TransferAuthorizationService.
 */
public class TransferAuthorizationService {

    /**
     * Validate the transferable amount
     *
     * @param connection the connection
     * @param senderAccount the sender account
     * @param receiverAccount the receiver account
     * @param amount the amount
     * @return the transfer amount dto
     * @throws SQLException the SQL exception
     * @throws BankingException 
     */
    public TransferAmountDTO validate(Connection connection,long senderAccount,long receiverAccount,long amount) throws SQLException, BankingException {
        AccountService acc=new AccountService();
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
