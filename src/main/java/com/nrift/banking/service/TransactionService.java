package com.nrift.banking.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.nrift.banking.dao.TransactionDAO;
import com.nrift.banking.exception.BankingException;
import com.nrift.banking.exception.OptimisticLockException;


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
    public void insertRowForTransferAmount(Connection connection,long senderAccountNo,long receiverAccountNo,long amount ) throws BankingException{
    	try{
        new TransactionDAO(connection).insertRowForTransferAmount(senderAccountNo,receiverAccountNo,amount);
    	}catch(SQLException | OptimisticLockException e){
    		throw new BankingException(e);
    	}
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

