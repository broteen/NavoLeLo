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
import com.nrift.banking.dto.TransferAmountDTO;
import com.nrift.banking.exception.BankingException;
import com.nrift.banking.service.DepositeService;
import com.nrift.banking.utility.DatasourceConnectionManager;

/**
 * The Class DepositeController.
 */
@WebServlet(name = "Deposite", urlPatterns = { "/DepositeController" })
public class DepositController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static Logger logger = Logger.getLogger(DepositController.class);

	/**
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		logger.info("Inside deposite controller class");
		long accountNumber=0L;
		long amount=0L;
		if(request.getParameter("accountNumber")!="")
			accountNumber= Long.parseLong(request.getParameter("accountNumber"));
		if(request.getParameter("amount")!="")
			amount =Long.parseLong( request.getParameter("amount"));
		String errorMsg = null;
		if (accountNumber ==0L) {
			errorMsg = "Account Number can not be empty";
		}
		else if (amount == 0L) {
			errorMsg = "Amount can not be  empty";
		}

		if (errorMsg != null) {
			RequestDispatcher rd = getServletContext().getRequestDispatcher(
					"/deposit.jsp");
			PrintWriter out = response.getWriter();
			out.println("<font color=red>" + errorMsg + "</font>");
			rd.include(request, response);
		} else {
			Connection con=null;
			try{
				con = DatasourceConnectionManager.getConnection((ComboPooledDataSource)getServletContext().getAttribute("datasource"));
				logger.info("Inside deposite controller class");
				TransferAmountDTO depositAmountDetails=new TransferAmountDTO(0L,accountNumber,amount,null);
				HttpSession session= request.getSession(false);
				long customerId  = new DepositeService().validateAccountNumber(con,accountNumber);

				logger.error("show the deposit details.....");
				session.setAttribute("depositAmountDetails", depositAmountDetails);
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/confirmDeposit.jsp");
				rd.forward(request, response);

				/*
				RequestDispatcher rd = getServletContext()
						.getRequestDispatcher("/deposite.jsp");
				PrintWriter out = response.getWriter();
				logger.error("Invalid Amount");
				out.println("<font color=red>Invalid amount</font>");
				rd.include(request, response);
				 */							
			}catch(BankingException e) {
				DatasourceConnectionManager.rollbackConnection(con);
				request.setAttribute("errorMsg", e.getMessage()); //There should be an error block on around the top of every jsp page 
				request.getRequestDispatcher("deposit.jsp").forward(request,response);
			}
		}
	}
}



