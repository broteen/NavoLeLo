package com.nrift.banking.utility;

import java.sql.Connection;

import javax.servlet.ServletException;

public class WithdrawAuthorizationManager {

	public WithdrawAmountDetails validate(Connection connection, long Account,
			long amount) throws ServletException {
		AccountManager acc = new AccountManager();
		AccountDetails AccountDetails = acc.getAccountDetails(connection,
				Account);
		if (AccountDetails == null)
			return null;
		if (AccountDetails.getBalance() > amount)
			return new WithdrawAmountDetails(Account, amount,
					AccountDetails.getUpdatedTime());
		else
			return null;
	}

}
