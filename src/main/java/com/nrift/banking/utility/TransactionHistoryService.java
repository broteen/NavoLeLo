package com.nrift.banking.utility;

import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;

public class TransactionHistoryService {

    public List<TransactionHistoryDTO> getTransactionHistoryDetails(Connection connection,long accountNo) throws ServletException {
        AccountService acc=new AccountService();
        AccountDTO accountDetails= acc.getAccountDetails(connection, accountNo);
        return new TransactionHistoryDAO(connection).getTransactionHistoryDetails(accountNo) ;
    }


}