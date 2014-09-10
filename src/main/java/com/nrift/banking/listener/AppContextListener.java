/**
 * 
 */
package com.nrift.banking.listener;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.*;
import org.apache.log4j.xml.DOMConfigurator;

import com.nrift.banking.utility.DBConnectionManager;

/**
 * @author zeeshank
 *
 */
@WebListener
public class AppContextListener implements ServletContextListener {

    /* (non-Javadoc)
     * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        Connection con = (Connection)servletContextEvent.getServletContext().getAttribute("connection");
        try {
            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /* (non-Javadoc)
     * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();

        String dburl = servletContext.getInitParameter("dburl");
        String dbpassword = servletContext.getInitParameter("dbpassword");
        String dbusername = servletContext.getInitParameter("dbusername");

        try {
            DBConnectionManager dbConnectionManager = new DBConnectionManager(dburl, dbusername, dbpassword);
            servletContext.setAttribute("connection", dbConnectionManager.getConnection());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

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
