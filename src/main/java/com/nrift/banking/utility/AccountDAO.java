

package com.nrift.banking.utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;

public class AccountDAO {

	private Connection connection;

	private Logger logger = Logger.getLogger(AccountDAO.class);

	public AccountDAO(Connection connection) {
		this.connection = connection;
	}
	
	private String getAllAccountQueryString() {
        return "SELECT B.ACCOUNT_NUMBER,A.ACCOUNT_TYPE,B.BALANCE,B.UPDATED_TIME FROM ACCOUNT_TYPE A "
                        + "INNER JOIN ACCOUNT B ON A.ACCOUNT_TYPE_ID = B.ACCOUNT_TYPE_ID  WHERE CUSTOMER_ID=?";
    }
    
    private String getAccountQueryString() {
        return "SELECT B.ACCOUNT_NUMBER,A.ACCOUNT_TYPE,B.BALANCE,B.UPDATED_TIME FROM ACCOUNT_TYPE A "
                        + "INNER JOIN ACCOUNT B ON A.ACCOUNT_TYPE_ID = B.ACCOUNT_TYPE_ID  WHERE B.ACCOUNT_NUMBER=?";
    }
    
    private String getCustomerQueryString() {
        return "select customer_id from account where account_number = ?";
    }
    
    private String getUpdatedTimeQueryString() {
        return "SELECT UPDATED_TIME FROM ACCOUNT WHERE ACCOUNT_NUMBER=? AND STATUS=?";
    }
    
    private String getWithdrawQueryString() {
        return "UPDATE ACCOUNT SET BALANCE=BALANCE-? WHERE ACCOUNT_NUMBER=? AND STATUS=?";
    }
    
    private String getDepositeQueryString() {
        return "UPDATE ACCOUNT SET BALANCE=BALANCE+? WHERE ACCOUNT_NUMBER=? AND STATUS=?";
    }
    
    private String getCloseAccountQueryString() {
        return "UPDATE ACCOUNT SET STATUS=? WHERE ACCOUNT_NUMBER=?";
    }
    
    private String getUpdatedByAndTimeQueryString() {
        return "UPDATE ACCOUNT SET UPDATED_BY=?,UPDATED_TIME=? WHERE ACCOUNT_NUMBER=? AND STATUS=?";
    }
    
    
	public List<AccountDTO> getAllAccountDetails(long customerId) throws SQLException{

		List<AccountDTO> list = new ArrayList<AccountDTO>();
        ResultSet rs = null;
        try {
            rs = DBUtils.getResultSetFromSQL(connection, getAllAccountQueryString(), customerId);
            if(rs != null) {
                while(rs.next()) {
                    AccountDTO validAccount = new AccountDTO(
                                    rs.getLong("ACCOUNT_NUMBER"),
                                    rs.getString("ACCOUNT_TYPE"),
                                    rs.getLong("BALANCE"),
                                    rs.getTimestamp("UPDATED_TIME"));
                    logger.info("Customer found with details=" + validAccount);
                    list.add(validAccount);
                }
            }
        } finally {
            DBUtils.closeResultSet(rs);
        }
        return list;

	}

	
	
	public AccountDTO getAccountDetails(long accountNo ) throws SQLException {
		
		AccountDTO accountDetails = null;
		ResultSet rs = null;
		try {

			
			rs = DBUtils.getResultSetFromSQL(connection, getAccountQueryString(), accountNo);
			if(rs !=null && rs.next())
			{
				accountDetails = new AccountDTO(rs.getLong("ACCOUNT_NUMBER"),rs.getString("ACCOUNT_TYPE"),rs.getLong("BALANCE"),rs.getTimestamp("UPDATED_TIME"));
				logger.info("Customer found with details="+accountDetails);
			}
			return accountDetails;
		}finally{
			DBUtils.closeResultSet(rs);
		}

	}
	
	
	public long getCustomerId(long accountNumber) throws SQLException
	{
		long customerID=0L; 
		ResultSet rs = null;
		
		try {
			rs=DBUtils.getResultSetFromSQL(connection, getCustomerQueryString(), accountNumber);
			if(rs !=null && rs.next()){
				customerID=rs.getLong("CUSTOMER_ID");
				String status=rs.getString("STATUS");
				if(status.equals("cancel")){
					customerID=0L;
				}
			}
		}finally{
			DBUtils.closeResultSet(rs);
        }
		return customerID;
	}

	public Timestamp getUpdatedTime(long accountNo) throws SQLException {
		Timestamp timestamp=null;
		ResultSet rs = null;
		try {

			rs = DBUtils.getResultSetFromSQL(connection,getUpdatedTimeQueryString(), accountNo,"normal");
			if(rs !=null && rs.next())
				timestamp=rs.getTimestamp("UPDATED_TIME");
		}
		finally{
			DBUtils.closeResultSet(rs);
		}
		return timestamp;
	}

	public int WithdrawAmount(long accountNo, long amount) throws SQLException {
		int updatedRows=0;
		try {
			updatedRows=DBUtils.getUpdateInfoFromSQL(connection, getWithdrawQueryString(), amount,accountNo,"normal");
			
		}finally{
		}
		return updatedRows;
	}

	public int CloseAccount(long accountNo)throws SQLException {
		int updatedRows=0;
		try {
			updatedRows=DBUtils.getUpdateInfoFromSQL(connection, getCloseAccountQueryString(),"cancel",accountNo);
		}finally{
		}
		return updatedRows;
	}
	
	public int DepositeAmount(long accountNo, long amount) throws SQLException{
		int updatedRows=0;
		try {
			updatedRows=DBUtils.getUpdateInfoFromSQL(connection, getDepositeQueryString(), amount,accountNo,"normal");
			
		}finally{
		}
		return updatedRows;
	}

	public int setUpdatedByandUpdatedTime(long accountNo,long userId) throws SQLException{
		int updatedRows=0;
		try {
			updatedRows=DBUtils.getUpdateInfoFromSQL(connection, getUpdatedByAndTimeQueryString(), userId,new Timestamp(new java.util.Date().getTime()),accountNo,"normal");
			
		}finally{
		}
		return updatedRows;
	}

}
