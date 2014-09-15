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

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.nrift.banking.dto.WithdrawAmountDTO;
import com.nrift.banking.exception.BankingException;
import com.nrift.banking.service.WithdrawAuthorizationService;
import com.nrift.banking.utility.DatasourceConnectionManager;

/**
 * Servlet implementation class WithdrawalAuthorizationController
 */
@WebServlet("/WithdrawalAuthorizationController")
public class WithdrawalAuthorizationController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    static Logger logger = Logger
            .getLogger(WithdrawalAuthorizationController.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public WithdrawalAuthorizationController() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
    	long account=0L;
    	long amount=0L;
    	if(request.getParameter("AccountNumber")!="")
    		account = Long.parseLong(request.getParameter("AccountNumber"));
    	if(request.getParameter("AccountNumber")!="")
    		amount = Long.parseLong(request.getParameter("Amount"));
        String errorMsg = null;
        if (account==0L) {
            errorMsg = "Please enter a valid account number";
        }
        else if(amount==0L){
            errorMsg = "Please enter a valid amount";
        }

        if (errorMsg != null) {
        	request.setAttribute("errorMsg",errorMsg);
            RequestDispatcher rd = getServletContext().getRequestDispatcher(
                    "/withdrawAmt.jsp");
            rd.forward(request, response);
        } else {

            Connection con = null;
            try{
            	con = DatasourceConnectionManager.getConnection((ComboPooledDataSource)getServletContext().getAttribute(
						"datasource"));
            	WithdrawAuthorizationService withdrawAuthorise = new WithdrawAuthorizationService();
                WithdrawAmountDTO withdrawAmountDetails= withdrawAuthorise.validate(con,account,amount);
                HttpSession session= request.getSession(false);
                if (withdrawAmountDetails!=null) {
                    logger.info("Transaction is Authorised");
                    session.setAttribute("withdrawAmountDetails", withdrawAmountDetails);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/withdrawUserConfirmation.jsp");
                    rd.forward(request, response);
                } else {
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/withdrawAmt.jsp");
                    PrintWriter out = response.getWriter();
                    logger.error("Transaction is Not Authorised");
                    out.println("<font color=red>Transaction Failed due to authorization failure</font>");
                    rd.include(request, response);
                }
            }catch(BankingException e) {
            	DatasourceConnectionManager.rollbackConnection(con);
				request.setAttribute("errorMsg", e.getMessage()); //There should be an error block on around the top of every jsp page
                request.getRequestDispatcher("withdrawAmt.jsp").forward(request,response);
            }
        }
    }

}
