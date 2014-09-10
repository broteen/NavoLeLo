package com.nrift.banking.utility;

import java.sql.Connection;
import java.sql.SQLException;


/**
 * The Class DepositeService.
 */
public class DepositeService {

    /**
     * Validate account number.
     *
     * @param con the con
     * @param accountNumber the account number
     * @return the long
     * @throws SQLException the SQL exception
     */
    public long validateAccountNumber(Connection con,long accountNumber) throws SQLException {

        return new AccountService().validateAccount(con,accountNumber);	
    }
}
