package com.nrift.banking.utility;

import java.sql.Connection;

import javax.servlet.ServletException;

public class WithdrawAuthorizationManager {

	public WithdrawAmountDTO validate(Connection connection, long account,
			long amount) throws ServletException {
		AccountManager acc = new AccountManager();
		AccountDTO AccountDetails = acc.getAccountDetails(connection,account);
		if (AccountDetails == null)
			return null;
		if (AccountDetails.getBalance() > amount)
			return new WithdrawAmountDTO(account, amount,AccountDetails.getUpdatedTime());
		else
			return null;
	}

}
