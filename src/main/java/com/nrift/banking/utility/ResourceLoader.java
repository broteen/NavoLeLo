package com.nrift.banking.utility;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ResourceLoader {

	/**
	 * Read a properties file from the classpath and return a Properties object
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public static Properties loadProperties(String filename) throws IOException{
		Properties properties = new Properties();
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		InputStream stream = loader.getResourceAsStream(filename);
		properties.load(stream);
		return properties;
	}

}
