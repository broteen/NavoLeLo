package com.nrift.banking.utility;

import java.sql.Connection;
import java.sql.SQLException;


public class DepositeService {

	public long validateAccountNumber(Connection con,long accountNumber) throws SQLException {
		
		return new AccountService().validateAccount(con,accountNumber);	
	}
}
