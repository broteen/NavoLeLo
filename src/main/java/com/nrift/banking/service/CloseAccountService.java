package com.nrift.banking.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;

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
	 * @throws SQLException 
	 */
	public boolean closedAccount(Connection connection, long accountNumber) throws SQLException {

		
			 if(new AccountService().IsAccountClosed(connection, accountNumber)){
				 connection.commit();
                 return true;
			 }
			 connection.rollback();         //See if we need to throw an exception here and remove con.rollback() from here
		     return false;
	}

}
