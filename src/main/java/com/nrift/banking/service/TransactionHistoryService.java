package com.nrift.banking.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;

import com.nrift.banking.dao.TransactionHistoryDAO;
import com.nrift.banking.dto.AccountDTO;
import com.nrift.banking.dto.TransactionHistoryDTO;

/**
 * The Class TransactionHistoryService.
 */
public class TransactionHistoryService {

    /**
     * Gets the transaction history details.
     *
     * @param connection the connection
     * @param accountNo the account no
     * @return the transaction history details
     * @throws SQLException the SQL exception
     */
    public List<TransactionHistoryDTO> getTransactionHistoryDetails(Connection connection,long accountNo) throws SQLException {
        AccountService acc=new AccountService();
        AccountDTO accountDetails= acc.getAccountDetails(connection, accountNo);
        return new TransactionHistoryDAO(connection).getTransactionHistoryDetails(accountNo) ;
    }
}