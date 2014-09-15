package com.nrift.banking.utility;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.nrift.banking.exception.BankingException;

public class DatasourceConnectionManager {

	private static Logger logger = Logger.getLogger(DatasourceConnectionManager.class);

	/**
	 * Gets the connection for the given {@link ComboPooledDataSource}.
	 *
	 * @param comboPooledDataSource the combo pooled data source
	 * @return the connection
	 * @throws BankingException the banking exception
	 */
	public static Connection getConnection(ComboPooledDataSource comboPooledDataSource) throws BankingException {
		try {
			Connection connection = comboPooledDataSource.getConnection();
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			connection.setAutoCommit(false);
			return connection;
		} catch (SQLException e) {
			logger.error("Failed to retrieve a connection from the connection pool", e);
			throw new BankingException("Failed to retrieve a connection from the connection pool", e);
		}
	}

	/**
	 * Closes the given {@link Connection}.
	 *
	 * @param connection the connection
	 */
	public static void closeConnection(Connection connection) {
		if(connection == null) {
			return;
		} else {
			try {
				connection.close();
			} catch (SQLException e) {
				logger.error("Failed to close the connection", e);
			}
		}
	}
	
	/**
	 * Rollbacks all transactions done with this {@link Connection}.
	 *
	 * @param connection the connection
	 */
	public static void rollbackConnection(Connection connection) {
		if(connection == null) {
			return;
		} else {
			try {
				connection.rollback();
			} catch (SQLException e) {
				logger.error("Failed to rollback the connection", e);
			}
		}
	}
	
}
