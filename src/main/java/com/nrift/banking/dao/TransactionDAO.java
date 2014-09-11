
package com.nrift.banking.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.ServletException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;

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

    /**
     * Gets the insert row for transfer query string.
     *
     * @return the insert row for transfer query string
     */
    
    private static final String INSERT_ROW_FOR_TRANSFER_QUERY_STRING= "insert into transaction(transaction_time,cr_acc_num,dr_acc_num,amount,transaction_ref) values(?,?,?,?,?)";
    /**
     * Gets the insert row for withdraw query string.
     *
     * @return the insert row for withdraw query string
     */
    
    private static final String INSERT_ROW_FOR_WITHDRAW_QUERY_STRING = "insert into transaction(transaction_time,dr_acc_num,amount,transaction_ref) values(?,?,?,?)"; 
    /**
     * Gets the insert row for deposite query string.
     *
     * @return the insert row for deposite query string
     */
   
    private static final String INSERT_ROW_FOR_DEPOSITE_QUERY_STRING = "insert into transaction(transaction_time,cr_acc_num,amount,transaction_ref) values(?,?,?,?)"; 

    /**
     * Insert row for transfer amount.
     *
     * @param senderAccountNo the sender account no
     * @param receiverAccountNo the receiver account no
     * @param amount the amount
     * @return the int
     * @throws SQLException the SQL exception
     */
    public int insertRowForTransferAmount(long senderAccountNo,long receiverAccountNo, long amount) throws SQLException {

        int updatedRows=0;
        try {
            updatedRows=DBHelper.getUpdateInfoFromSQL(connection, INSERT_ROW_FOR_TRANSFER_QUERY_STRING,new Timestamp(new java.util.Date().getTime()),receiverAccountNo,
                    senderAccountNo,amount,TransactionRefGenerator.getInstance().getCounter());

        }finally{
        }
        return updatedRows;
    }

    /**
     * Insert row for withdraw amount.
     *
     * @param accountNo the account no
     * @param amount the amount
     * @return the int
     * @throws SQLException the SQL exception
     */
    public int insertRowForWithdrawAmount(long accountNo, long amount) throws SQLException {

        int updatedRows=0;
        try {
            updatedRows=DBHelper.getUpdateInfoFromSQL(connection, INSERT_ROW_FOR_WITHDRAW_QUERY_STRING,new Timestamp(new java.util.Date().getTime()),accountNo,
                    amount,TransactionRefGenerator.getInstance().getCounter());

        }finally{
        }
        return updatedRows;
    }

    /**
     * Insert row for deposite amount.
     *
     * @param accountNo the account no
     * @param amount the amount
     * @return the int
     * @throws SQLException the SQL exception
     */
    public int insertRowForDepositeAmount(long accountNo, long amount) throws SQLException {
        int updatedRows=0;
        try {
            updatedRows=DBHelper.getUpdateInfoFromSQL(connection, INSERT_ROW_FOR_DEPOSITE_QUERY_STRING,new Timestamp(new java.util.Date().getTime()),accountNo,
                    amount,TransactionRefGenerator.getInstance().getCounter());	
        }finally{
        }
        return updatedRows;
    }
}

