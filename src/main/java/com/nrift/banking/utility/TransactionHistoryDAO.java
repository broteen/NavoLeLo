

package com.nrift.banking.utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;

public class TransactionHistoryDAO {

	private Connection connection;

	private Logger logger = Logger.getLogger(TransactionHistoryDAO.class);

	public TransactionHistoryDAO(Connection connection) {
		this.connection = connection;
	}

	public List<TransactionHistoryDetails> getTransactionHistoryDetails(long accountNo) throws ServletException{

		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			ps = connection.prepareStatement("SELECT TRANSACTION_REF, TRANSACTION_TIME, CR_ACC_NUM, DR_ACC_NUM, AMOUNT FROM TRANSACTION WHERE CR_NUM = ? OR DR_NUM = ?");

			ps.setLong(1,accountNo);
			rs = ps.executeQuery();
			if(rs !=null)
			{


				List<TransactionHistoryDetails> list= new LinkedList<TransactionHistoryDetails>();
				while (rs.next()) 
				{
					TransactionHistoryDetails transaction = new TransactionHistoryDetails(rs.getLong("TRANSACTION_REF"),rs.getTimestamp("TRANSACTION_TIME"),rs.getLong("CR_ACCNUM"), rs.getLong("DR_ACCNUM"), rs.getLong("AMOUNT"));
					logger.info("Transaction is shown="+transaction);
					list.add(transaction);
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

}
