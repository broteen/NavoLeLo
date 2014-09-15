package com.nrift.banking.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


import com.nrift.banking.dao.TransactionDAO;
import com.nrift.banking.dto.TransactionHistoryDTO;
import com.nrift.banking.exception.BankingException;

/**
 * The Class TransactionHistoryService.
 */
public class TransactionHistoryService {

    /**
     * Gets the transaction history details.
     *TransactionDAO.javaTransactionDAO.java
     * @param connection the connection
     * @param accountNo the account no
     * @return the transaction history details
     * @throws SQLException the SQL exception
     * @throws BankingException 
     */
    public List<TransactionHistoryDTO> getTransactionHistoryDetails(Connection connection,long accountNo) throws BankingException {
    	try{
        return new TransactionDAO(connection).getTransactionHistoryDetails(accountNo) ;
    	}catch(SQLException e){
    		throw new BankingException(e);
    	}
    }
}