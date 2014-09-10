
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

public class TransactionDAO {
	private Connection connection;
	private Logger logger = Logger.getLogger(UserValidationDAO.class);
	public TransactionDAO(Connection connection) {
		this.connection=connection;
	}
	
	private String getInsertRowForTransferQueryString() {
        return "INSERT INTO TRANSACTION(TRANSACTION_TIME,CR_ACC_NUM,DR_ACC_NUM,AMOUNT,TRANSACTION_REF) VALUES(?,?,?,?,?)";
    }
	
	private String getInsertRowForWithdrawQueryString() {
        return "INSERT INTO TRANSACTION(TRANSACTION_TIME,DR_ACC_NUM,AMOUNT,TRANSACTION_REF) VALUES(?,?,?,?)";
    }
	
	private String getInsertRowForDepositeQueryString() {
        return "INSERT INTO TRANSACTION(TRANSACTION_TIME,CR_ACC_NUM,AMOUNT,TRANSACTION_REF) VALUES(?,?,?,?)";
    }
	
	
	public int insertRowForTransferAmount(long senderAccountNo,long receiverAccountNo, long amount) throws SQLException {
		
		int updatedRows=0;
		try {
			updatedRows=DBUtils.getUpdateInfoFromSQL(connection, getInsertRowForTransferQueryString(),new Timestamp(new java.util.Date().getTime()),receiverAccountNo,
					senderAccountNo,amount,TransactionRefGenerator.getInstance().getCounter());
			
		}finally{
		}
		return updatedRows;
	}

	public int insertRowForWithdrawAmount(long accountNo, long amount) throws SQLException {
		
		int updatedRows=0;
		try {
			updatedRows=DBUtils.getUpdateInfoFromSQL(connection, getInsertRowForWithdrawQueryString(),new Timestamp(new java.util.Date().getTime()),accountNo,
					amount,TransactionRefGenerator.getInstance().getCounter());
			
		}finally{
		}
		return updatedRows;
	}

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

