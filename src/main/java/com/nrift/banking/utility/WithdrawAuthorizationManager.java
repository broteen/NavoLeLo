package com.nrift.banking.utility;

import java.sql.Connection;

import javax.servlet.ServletException;

public class WithdrawAuthorizationManager {

	public WithdrawAmountDetails validate(Connection connection, long account,
			long amount) throws ServletException {
		AccountManager acc = new AccountManager();
		AccountDetails AccountDetails = acc.getAccountDetails(connection,account);
		if (AccountDetails == null)
			return null;
		if (AccountDetails.getBalance() > amount)
			return new WithdrawAmountDetails(account, amount,AccountDetails.getUpdatedTime());
		else
			return null;
	}

}
