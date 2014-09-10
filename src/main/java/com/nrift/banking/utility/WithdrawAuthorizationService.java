package com.nrift.banking.utility;

import java.sql.Connection;
import java.sql.SQLException;


public class WithdrawAuthorizationService {

	public WithdrawAmountDTO validate(Connection connection, long account,
			long amount) throws SQLException  {
		AccountService acc = new AccountService();
		AccountDTO AccountDetails = acc.getAccountDetails(connection,account);
		if (AccountDetails == null)
			return null;
		if (AccountDetails.getBalance() > amount)
			return new WithdrawAmountDTO(account, amount,AccountDetails.getUpdatedTime());
		else
			return null;
	}

}
