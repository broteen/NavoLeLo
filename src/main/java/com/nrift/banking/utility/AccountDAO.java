

package com.nrift.banking.utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

	public List<AccountDetails> getAllAccountDetails(long customerId) throws ServletException{

		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			ps = connection.prepareStatement("SELECT B.ACCOUNT_NUMBER,A.ACCOUNT_TYPE,B.BALANCE,B.UPDATED_TIME FROM ACCOUNT_TYPE A "
					+ "INNER JOIN ACCOUNT B ON A.ACCOUNT_TYPE_ID = B.ACCOUNT_TYPE_ID  WHERE CUSTOMER_ID=? AND B.STATUS=?");

			ps.setLong(1,customerId);
			ps.setString(2, "normal");
			rs = ps.executeQuery();

			if(rs !=null)
			{


				List<AccountDetails> list= new ArrayList<AccountDetails>();
				while (rs.next()) 
				{
					AccountDetails validAccount = new AccountDetails(rs.getLong("ACCOUNT_NUMBER"),rs.getString("ACCOUNT_TYPE"),rs.getLong("BALANCE"),rs.getTimestamp("UPDATED_TIME"));
					logger.info("Customer found with details="+validAccount);
					list.add(validAccount);
				}

				return (list);

			}

			return null;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("SQLException in exracting data from the ResultSet");
			System.out.println(e);
			throw new ServletException("DB Connection problem.");
		}

		finally{
			try {
				rs.close();
				ps.close();
			} catch (SQLException e) {
				logger.error("SQLException in closing PreparedStatement or ResultSet");
			}

		}

	}

	public AccountDetails getAccountDetails(long accountNo ) throws ServletException {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			ps = connection.prepareStatement("SELECT B.ACCOUNT_NUMBER,A.ACCOUNT_TYPE,B.BALANCE,B.UPDATED_TIME FROM ACCOUNT_TYPE A "
					+ "INNER JOIN ACCOUNT B ON A.ACCOUNT_TYPE_ID = B.ACCOUNT_TYPE_ID  WHERE B.ACCOUNT_NUMBER=? AND B.STATUS=?");

			ps.setLong(1,accountNo);
			ps.setString(2, "normal");
			rs = ps.executeQuery();
			if(rs !=null && rs.next())
			{
				AccountDetails validAccount = new AccountDetails(rs.getLong("ACCOUNT_NUMBER"),rs.getString("ACCOUNT_TYPE"),rs.getLong("BALANCE"),rs.getTimestamp("UPDATED_TIME"));
				logger.info("Customer found with details="+validAccount);
				return(validAccount);
			}
			return null;
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("SQLException in exracting data from the ResultSet");
			System.out.println(e);
			throw new ServletException("DB Connection problem.");
		}

		finally{
			try {
				rs.close();
				ps.close();
			} catch (SQLException e) {
			logger.error("SQLException in closing PreparedStatement or ResultSet");
			}

		}
	}
	
	public long getCustomerId(long accountNumber) throws ServletException
	{
		long customerID=0L; 
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			ps = connection.prepareStatement("SELECT CUSTOMER_ID,STATUS FROM ACCOUNT WHERE ACCOUNT_NUMBER=? AND STATUS=?");
					
			ps.setLong(1,accountNumber);
			ps.setString(2, "normal");
			rs = ps.executeQuery();
			if(rs !=null)
			{
				customerID=rs.getInt("CUSTOMER_ID");
				String status=rs.getString("STATUS");
				if(status=="normal")
				{
					customerID=0L;
				}
			}
				return customerID;
	}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("SQLException in exracting data from the ResultSet");
			System.out.println(e);
			throw new ServletException("DB Connection problem.");
		}
		finally{
            try {
                rs.close();
                ps.close();
            } catch (SQLException e) {
                logger.error("SQLException in closing PreparedStatement or ResultSet");
            }
             
        }
		
	}
}
