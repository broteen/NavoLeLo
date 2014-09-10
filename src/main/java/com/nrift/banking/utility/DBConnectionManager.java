package com.nrift.banking.utility;

import java.sql.Connection;
import java.sql.SQLException;

import oracle.jdbc.pool.OracleDataSource;

/**
 * The Class DBConnectionManager.
 */
public class DBConnectionManager {
    private Connection connection;

    /**
     * Instantiates a new DB connection manager.
     *
     * @param dburl the dburl
     * @param dbusername the dbusername
     * @param dbpassword the dbpassword
     * @throws SQLException the SQL exception
     */
    public DBConnectionManager(String dburl, String dbusername, String dbpassword) throws SQLException {
        OracleDataSource ds = new OracleDataSource();
        ds.setDriverType("thin");
        ds.setURL(dburl);
        this.connection = ds.getConnection(dbusername, dbpassword);
    }

    /**
     * Gets the connection.
     *
     * @return the connection
     */
    public Connection getConnection() {
        return connection;
    }
}
