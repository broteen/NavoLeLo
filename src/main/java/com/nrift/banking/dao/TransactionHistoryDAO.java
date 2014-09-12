package com.nrift.banking.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.nrift.banking.dto.TransactionHistoryDTO;
import com.nrift.banking.exception.BankingException;
import com.nrift.banking.utility.DBHelper;

/**
 * The Class TransactionHistory-Data Access Object.
 */
public class TransactionHistoryDAO {

    private Connection connection;

    private Logger logger = Logger.getLogger(TransactionHistoryDAO.class);

    /**
     * Instantiates a new transaction history dao.
     *
     * @param connection the connection
     */
    public TransactionHistoryDAO(Connection connection) {
        this.connection = connection;
    }



    /** The Constant TRANSACTION_HISTORY_QUERY_STRING. */
    private static final String TRANSACTION_HISTORY_QUERY_STRING= "select transaction_ref, transaction_time, cr_acc_num, dr_acc_num, "
            + "amount from transaction where cr_num = ? or dr_num = ?";

    /**
     * Gets the transaction history details.
     *
     * @param accountNo the account no
     * @return the transaction history details
     * @throws SQLException the SQL exception
     * @throws BankingException 
     */
    public List<TransactionHistoryDTO> getTransactionHistoryDetails(long accountNo) throws SQLException, BankingException{
        ResultSet rs = null;
        try {
            rs = DBHelper.getResultSetFromSQL(connection, TRANSACTION_HISTORY_QUERY_STRING,accountNo,accountNo);
            if(rs !=null){
            	List<TransactionHistoryDTO> list= new ArrayList<TransactionHistoryDTO>();
                while (rs.next()){
                    TransactionHistoryDTO transaction = new TransactionHistoryDTO(rs.getLong("TRANSACTION_REF"),rs.getTimestamp("TRANSACTION_TIME"),rs.getLong("CR_ACCNUM"), rs.getLong("DR_ACCNUM"), rs.getLong("AMOUNT"));
                    logger.info("Transaction is shown="+transaction);
                    list.add(transaction);
                }
                if(!list.isEmpty())
                	return list; 
            }
            throw new BankingException("Transaction History Details are Empty");
        }finally{
            DBHelper.closeResultSet(rs);
        }
    }

}
