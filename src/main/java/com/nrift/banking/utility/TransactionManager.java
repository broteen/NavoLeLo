<<<<<<< Upstream, based on master
package com.nrift.banking.utility;

public class TransactionManager {

	
	
}
=======
package com.nrift.banking.utility;

import java.sql.Connection;

public class TransactionManager {

	public int insertRowForTransferAmount(Connection connection,long senderAccountNo,long receiverAccountNo,long amount ) {
		return new TransactionDAO(connection).insertRowForTransferAmount(senderAccountNo,receiverAccountNo,amount);
	}

}
>>>>>>> a9cb478 Transfer Amount is almost done
