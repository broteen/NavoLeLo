package com.nrift.banking.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;

import com.nrift.banking.exception.BankingException;

// TODO: Auto-generated Javadoc
/**
 * The Class CloseAccountService.
 */
public class CloseAccountService extends AccountService {
	
	/**
	 * Closed account.
	 *
	 * @param connection the con
	 * @param accountNumber the account number
	 * @return true, if successful
	 * @throws BankingException 
	 * @throws SQLException 
	 */
	public void closeAccount(Connection connection, long accountNumber) throws BankingException {
			try{
			 new AccountService().closeAccount(connection, accountNumber);
				 connection.commit();
			 }catch(SQLException e){
		    		throw new BankingException(e);
		    }
			 
	}

}
