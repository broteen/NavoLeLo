package com.nrift.banking.utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;

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

    /**
     * Gets the transaction historye query string.
     *
     * @return the transaction historye query string
     */
    private String getTransactionHistoryeQueryString() {
        return "SELECT TRANSACTION_REF, TRANSACTION_TIME, CR_ACC_NUM, DR_ACC_NUM, AMOUNT FROM TRANSACTION WHERE CR_NUM = ? OR DR_NUM = ?";
    }

    /**
     * Gets the transaction history details.
     *
     * @param accountNo the account no
     * @return the transaction history details
     * @throws SQLException the SQL exception
     */
    public List<TransactionHistoryDTO> getTransactionHistoryDetails(long accountNo) throws SQLException{

        List<TransactionHistoryDTO> list= null;
        ResultSet rs = null;
        try {
            rs = DBUtils.getResultSetFromSQL(connection, getTransactionHistoryeQueryString(),accountNo,accountNo);
            if(rs !=null){
                list= new ArrayList<TransactionHistoryDTO>();
                while (rs.next()){
                    TransactionHistoryDTO transaction = new TransactionHistoryDTO(rs.getLong("TRANSACTION_REF"),rs.getTimestamp("TRANSACTION_TIME"),rs.getLong("CR_ACCNUM"), rs.getLong("DR_ACCNUM"), rs.getLong("AMOUNT"));
                    logger.info("Transaction is shown="+transaction);
                    list.add(transaction);
                }
            }
        }finally{
            DBUtils.closeResultSet(rs);
        }
        return list;
    }

}
