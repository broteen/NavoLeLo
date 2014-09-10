
package com.nrift.banking.utility;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;

public class TransactionService {

	public int insertRowForTransferAmount(Connection connection,long senderAccountNo,long receiverAccountNo,long amount ) throws SQLException {
		return new TransactionDAO(connection).insertRowForTransferAmount(senderAccountNo,receiverAccountNo,amount);
	}

	public int insertRowForWithdrawAmount(Connection connection,
			long accountNo, long amount) throws SQLException  {
		// TODO Auto-generated method stub
		return new TransactionDAO(connection).insertRowForWithdrawAmount(accountNo,amount);
	}

}

