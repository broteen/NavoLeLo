package com.nrift.banking;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.nrift.banking.utility.DatasourceManager;

public class DAOTest {
	private static Connection connection;
	private static ComboPooledDataSource comboPooledDataSource;
	
	@BeforeClass
    public static void setup(){
        try {
        	comboPooledDataSource = DatasourceManager.INSTANCE.getDataSource();
        	connection = comboPooledDataSource.getConnection();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
	
	@AfterClass
    public static void teardown() {
        try {
            connection.close();
            comboPooledDataSource.close();
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
	
	public Connection getConnection() {
		return connection;
	}
}
