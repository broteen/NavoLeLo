/**
 * 
 */
package com.nrift.banking.listener;

import java.io.File;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.xml.DOMConfigurator;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;
import com.nrift.banking.utility.DatasourceManager;

@WebListener
public class AppContextListener implements ServletContextListener {

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		ComboPooledDataSource dataSource = (ComboPooledDataSource) servletContextEvent.getServletContext().getAttribute("datasource");
		try  {
			dataSource.close();			
		} catch(Exception e) {
			//no-op;
		}
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		ServletContext servletContext = servletContextEvent.getServletContext();
		servletContext.setAttribute("datasource",DatasourceManager.INSTANCE.getDataSource());

		String log4jConfig = servletContext.getInitParameter("log4j-config");
		if(log4jConfig == null){
			System.err.println("No log4j-config init param, initializing log4j with BasicConfigurator");
			BasicConfigurator.configure();
		}else {
			String webAppPath = servletContext.getRealPath("/");
			String log4jProp = webAppPath + log4jConfig;
			File log4jConfigFile = new File(log4jProp);
			if (log4jConfigFile.exists()) {
				System.out.println("Initializing log4j with: " + log4jProp);
				DOMConfigurator.configure(log4jProp);
			} else {
				System.err.println(log4jProp + " file not found, initializing log4j with BasicConfigurator");
				BasicConfigurator.configure();
			}
		}

	}

}
