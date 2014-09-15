
package com.nrift.banking.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.nrift.banking.dto.TransactionHistoryDTO;
import com.nrift.banking.exception.BankingException;
import com.nrift.banking.exception.OptimisticLockException;
import com.nrift.banking.utility.DBHelper;
import com.nrift.banking.utility.TransactionRefGenerator;

/**
 * The Class Transaction-Data Access Object.
 */
public class TransactionDAO {
    private Connection connection;
    private Logger logger = Logger.getLogger(UserValidationDAO.class);

    /**
     * Instantiates a new transaction dao.
     *
     * @param connection the connection
     */
    public TransactionDAO(Connection connection) {
        this.connection=connection;
    }


    
    /** The Constant INSERT_ROW_FOR_TRANSFER_QUERY_STRING. */
    private static final String INSERT_ROW_FOR_TRANSFER_QUERY_STRING= "insert into transaction(transaction_time,cr_acc_num,dr_acc_num,amount,transaction_ref) values(?,?,?,?,?)";
 
    
    /** The Constant INSERT_ROW_FOR_WITHDRAW_QUERY_STRING. */
    private static final String INSERT_ROW_FOR_WITHDRAW_QUERY_STRING = "insert into transaction(transaction_time,dr_acc_num,amount,transaction_ref) values(?,?,?,?)"; 

   
    /** The Constant INSERT_ROW_FOR_DEPOSITE_QUERY_STRING. */
    private static final String INSERT_ROW_FOR_DEPOSITE_QUERY_STRING = "insert into transaction(transaction_time,cr_acc_num,amount,transaction_ref) values(?,?,?,?)"; 
    
    /** The Constant TRANSACTION_HISTORY_QUERY_STRING. */
    private static final String TRANSACTION_HISTORY_QUERY_STRING= "select transaction_ref, transaction_time, cr_acc_num, dr_acc_num, "
            + "amount from transaction where cr_acc_num = ? or dr_acc_num = ?";

    /**
     * Insert row for transfer amount.
     *
     * @param senderAccountNo the sender account no
     * @param receiverAccountNo the receiver account no
     * @param amount the amount
     * @return the int
     * @throws SQLException the SQL exception
     * @throws OptimisticLockException 
     */
    public void insertRowForTransferAmount(long senderAccountNo,long receiverAccountNo, long amount) throws SQLException, OptimisticLockException {

        try {
            int updatedRows=DBHelper.getUpdateInfoFromSQL(connection, INSERT_ROW_FOR_TRANSFER_QUERY_STRING,new Timestamp(new java.util.Date().getTime()),receiverAccountNo,
                    senderAccountNo,amount,TransactionRefGenerator.getInstance().getCounter());
            if(updatedRows==0)
            	throw new OptimisticLockException("Error in inserting rows in transaction table while transferring amount");

        }finally{
        }
    }

    /**
     * Insert row for withdraw amount.
     *
     * @param accountNo the account no
     * @param amount the amount
     * @return the int
     * @throws SQLException the SQL exception
     * @throws OptimisticLockException 
     */
    public void insertRowForWithdrawAmount(long accountNo, long amount) throws SQLException, OptimisticLockException {

        try {
            int updatedRows=DBHelper.getUpdateInfoFromSQL(connection, INSERT_ROW_FOR_WITHDRAW_QUERY_STRING,new Timestamp(new java.util.Date().getTime()),accountNo,
                    amount,TransactionRefGenerator.getInstance().getCounter());
            if(updatedRows==0)
            	throw new OptimisticLockException("Error in inserting rows in transaction table while withdrawing amount");

        }finally{
        }
    }

    /**
     * Insert row for deposite amount.
     *
     * @param accountNo the account no
     * @param amount the amount
     * @throws SQLException the SQL exception
     * @throws OptimisticLockException 
     */
    public void insertRowForDepositeAmount(long accountNo, long amount) throws SQLException, OptimisticLockException {     
        try {
            int updatedRows=DBHelper.getUpdateInfoFromSQL(connection, INSERT_ROW_FOR_DEPOSITE_QUERY_STRING,new Timestamp(new java.util.Date().getTime()),accountNo,
                    amount,TransactionRefGenerator.getInstance().getCounter());	
            if(updatedRows==0)
            	throw new OptimisticLockException("Error in inserting rows in transaction table while depositing amount");
        }finally{
        }
    }
    
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
                    TransactionHistoryDTO transaction = new TransactionHistoryDTO(rs.getLong("TRANSACTION_REF"),rs.getTimestamp("TRANSACTION_TIME"),rs.getLong("CR_ACC_NUM"), rs.getLong("DR_ACC_NUM"), rs.getLong("AMOUNT"));
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

