
package com.nrift.banking.utility;

import java.sql.Connection;

import javax.servlet.ServletException;

public class TransactionManager {

	public int insertRowForTransferAmount(Connection connection,long senderAccountNo,long receiverAccountNo,long amount ) throws ServletException {
		return new TransactionDAO(connection).insertRowForTransferAmount(senderAccountNo,receiverAccountNo,amount);
	}

}

