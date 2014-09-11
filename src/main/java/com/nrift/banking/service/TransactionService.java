package com.nrift.banking.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.nrift.banking.dao.TransactionDAO;


/**
 * The Class TransactionService.
 */
public class TransactionService {

    /**
     * Insert row for transfer amount.
     *
     * @param connection the connection
     * @param senderAccountNo the sender account no
     * @param receiverAccountNo the receiver account no
     * @param amount the amount
     * @return the int
     * @throws SQLException the SQL exception
     */
    public int insertRowForTransferAmount(Connection connection,long senderAccountNo,long receiverAccountNo,long amount ) throws SQLException {
        return new TransactionDAO(connection).insertRowForTransferAmount(senderAccountNo,receiverAccountNo,amount);
    }

    /**
     * Insert row for withdraw amount.
     *
     * @param connection the connection
     * @param accountNo the account no
     * @param amount the amount
     * @return the int
     * @throws SQLException the SQL exception
     */
    public int insertRowForWithdrawAmount(Connection connection,
            long accountNo, long amount) throws SQLException  {
        // TODO Auto-generated method stub
        return new TransactionDAO(connection).insertRowForWithdrawAmount(accountNo,amount);
    }

    /**
     * Insert row for deposite amount.
     *
     * @param connection the connection
     * @param accountNo the account no
     * @param amount the amount
     * @return the int
     * @throws SQLException the SQL exception
     */
    public int insertRowForDepositeAmount(Connection connection,
            long accountNo, long amount)throws SQLException {
        return new TransactionDAO(connection).insertRowForDepositeAmount(accountNo,amount);
    }

}

