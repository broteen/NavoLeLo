package com.nrift.banking.utility;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public enum DatasourceManager {
	INSTANCE;

	private Properties props;
	private ComboPooledDataSource cpds;
	private Logger log = Logger.getLogger(DatasourceManager.class);

	private DatasourceManager() {
		try {
			log.info("Reading datasource.properties from classpath");
			props = ResourceLoader.loadProperties("datasource.properties");
		} catch (IOException e) {
			log.error("Failed to read datasource parameters", e);
			throw new ExceptionInInitializerError(e);
		}
		cpds = new ComboPooledDataSource();
		cpds.setJdbcUrl(props.getProperty("jdbcUrl"));
		cpds.setUser(props.getProperty("username"));
		cpds.setPassword(props.getProperty("password"));
		try {
			cpds.setDriverClass(props.getProperty("driverClass"));
		} catch (PropertyVetoException e) {
			log.error("Failed to load Driver class", e);
		}

		cpds.setInitialPoolSize(new Integer((String) props
				.getProperty("initialPoolSize")));
		cpds.setAcquireIncrement(new Integer((String) props
				.getProperty("acquireIncrement")));
		cpds.setMaxPoolSize(new Integer((String) props
				.getProperty("maxPoolSize")));
		cpds.setMinPoolSize(new Integer((String) props
				.getProperty("minPoolSize")));
		cpds.setMaxStatements(new Integer((String) props
				.getProperty("maxStatements")));
	}

	/**
	 * Gets the {@link ComboPooledDataSource}.
	 *
	 * @return the data source
	 */
	public ComboPooledDataSource getDataSource() {
		return cpds;
	}
}
