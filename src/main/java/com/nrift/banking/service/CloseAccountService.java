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
	 * @param con the con
	 * @param accountNumber the account number
	 * @return true, if successful
	 * @throws SQLException 
	 */
	public boolean closedAccount(Connection con, long accountNumber) throws SQLException {

		try {
			 if(new AccountService().IsAccountClosed(con, accountNumber)){
				 con.commit();
				 con.setAutoCommit(true);
                 return true;
			 }else{
				 con.rollback();
		            con.setAutoCommit(true);
		            return false;
			 }
		}

		catch (ServletException e) {
			// To be Implemented later this is not the correct implementation
			return false;
		}
	}

}
