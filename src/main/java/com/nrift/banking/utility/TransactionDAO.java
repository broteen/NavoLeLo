
package com.nrift.banking.utility;
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

/**
 * The Class TransactionDAO.
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
    private String getInsertRowForTransferQueryString() {
        return "INSERT INTO TRANSACTION(TRANSACTION_TIME,CR_ACC_NUM,DR_ACC_NUM,AMOUNT,TRANSACTION_REF) VALUES(?,?,?,?,?)";
    }

    /**
     * Gets the insert row for withdraw query string.
     *
     * @return the insert row for withdraw query string
     */
    private String getInsertRowForWithdrawQueryString() {
        return "INSERT INTO TRANSACTION(TRANSACTION_TIME,DR_ACC_NUM,AMOUNT,TRANSACTION_REF) VALUES(?,?,?,?)";
    }

    /**
     * Gets the insert row for deposite query string.
     *
     * @return the insert row for deposite query string
     */
    private String getInsertRowForDepositeQueryString() {
        return "INSERT INTO TRANSACTION(TRANSACTION_TIME,CR_ACC_NUM,AMOUNT,TRANSACTION_REF) VALUES(?,?,?,?)";
    }


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
            updatedRows=DBUtils.getUpdateInfoFromSQL(connection, getInsertRowForTransferQueryString(),new Timestamp(new java.util.Date().getTime()),receiverAccountNo,
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
            updatedRows=DBUtils.getUpdateInfoFromSQL(connection, getInsertRowForWithdrawQueryString(),new Timestamp(new java.util.Date().getTime()),accountNo,
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
            updatedRows=DBUtils.getUpdateInfoFromSQL(connection, getInsertRowForDepositeQueryString(),new Timestamp(new java.util.Date().getTime()),accountNo,
                    amount,TransactionRefGenerator.getInstance().getCounter());	
        }finally{
        }
        return updatedRows;
    }
}

