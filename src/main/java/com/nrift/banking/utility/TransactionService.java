package com.nrift.banking.utility;

import java.sql.Connection;

import javax.servlet.ServletException;

/**
 * The Class TransactionService.
 */
public class TransactionService {

    /**
     * Call DAO to insert row for transfer amount into Transaction table
     *
     * @param connection the connection
     * @param senderAccountNo the sender account no
     * @param receiverAccountNo the receiver account no
     * @param amount the amount
     * @return the int
     * @throws ServletException the servlet exception
     */
    public int insertRowForTransferAmount(Connection connection,long senderAccountNo,long receiverAccountNo,long amount ) throws ServletException {
        return new TransactionDAO(connection).insertRowForTransferAmount(senderAccountNo,receiverAccountNo,amount);
    }

    /**
     * Call DAO to insert row for withdraw amount into Transaction table
     *
     * @param connection the connection
     * @param accountNo the account no
     * @param amount the amount
     * @return the int
     * @throws ServletException the servlet exception
     */
    public int insertRowForWithdrawAmount(Connection connection,
            long accountNo, long amount) throws ServletException {
        // TODO Auto-generated method stub
        return new TransactionDAO(connection).insertRowForWithdrawAmount(accountNo,amount);
    }

}

