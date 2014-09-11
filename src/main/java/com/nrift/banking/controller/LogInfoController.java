package com.nrift.banking.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.nrift.banking.dto.UserDTO;
import com.nrift.banking.service.LogInfoService;
import com.nrift.banking.service.UserValidationService;

/**
 * The Class LogInfoController.
 */
@WebServlet(name = "LogInfo", urlPatterns = { "/LogInfoController" })
public class LogInfoController  extends HttpServlet {

    private static final long serialVersionUID = 1L;

    static Logger logger = Logger.getLogger(LoginController.class);

    /**
     * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String errorMsg = null;
        if (username == null || username.equals("")) {
            errorMsg = "User Name can't be null or empty";
        }
        if (password == null || password.equals("")) {
            errorMsg = "Password can't be null or empty";
        }

        if (errorMsg != null) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher(
                    "/loginfo.html");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>" + errorMsg + "</font>");
            rd.include(request, response);
        } else {

            Connection con = (Connection) getServletContext().getAttribute(
                    "connection");
            LogInfoService logInfo=new LogInfoService();

            try{
                boolean user=logInfo.validateUsername(con, username);

                if (user==false) {
                    logger.info("Username registered");
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user);
                    //System.out.println(user.getCustomerDetails().getName());
                    System.out.print("hi");
                    response.sendRedirect("loginsuccess.html");
                } else {
                    RequestDispatcher rd = getServletContext()
                            .getRequestDispatcher("/login.html");
                    PrintWriter out = response.getWriter();
                    logger.error("Username already exists in database");
                    out.println("<font color=red>Sorry! username already exists in the database</font>");
                    rd.include(request, response);
                }
            }catch(SQLException |ServletException| IOException e) {
                try {
                    con.rollback();
                } catch(SQLException e1) {
                    logger.error("Rollback error");
                }
                logger.error(" Exception Thrown");
                //There should be an error block on around the top of every jsp page
                request.setAttribute("errorMsg", "Exception Occured!");
                request.getRequestDispatcher("loginfo.html").forward(request,response);
            }
        }
    }

}
