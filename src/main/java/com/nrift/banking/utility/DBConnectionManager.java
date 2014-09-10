package com.nrift.banking.utility;

import java.sql.Connection;
import java.sql.SQLException;

import oracle.jdbc.pool.OracleDataSource;

public class DBConnectionManager {
	private Connection connection;
	
	public DBConnectionManager(String dburl, String dbusername, String dbpassword) throws SQLException {
		OracleDataSource ds = new OracleDataSource();
		ds.setDriverType("thin");
		ds.setURL(dburl);
		this.connection = ds.getConnection(dbusername, dbpassword);
	}

	public Connection getConnection() {
		return connection;
	}
}
