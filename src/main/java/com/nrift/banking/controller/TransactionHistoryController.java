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
import com.nrift.banking.dto.TransactionHistoryDTO;
import com.nrift.banking.dto.TransactionViewDTO;
import com.nrift.banking.exception.BankingException;
import com.nrift.banking.service.TransactionHistoryService;
import com.nrift.banking.service.TransactionViewService;
import com.nrift.banking.utility.DatasourceConnectionManager;

/**
 * The Class TransactionHistoryController.
 */
@WebServlet(name = "TransactionHistory", urlPatterns = { "/transactionHistory" })
public class TransactionHistoryController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	static Logger logger = Logger.getLogger(LoginController.class);

	/**
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		long accountNo = Long.parseLong(request.getParameter("accountNo"));

		Connection con = null;
		

		try{
			con = DatasourceConnectionManager.getConnection((ComboPooledDataSource)getServletContext().getAttribute(
					"datasource"));
			TransactionViewService transactionViewManager = new TransactionViewService();
			TransactionViewDTO transactionViewDetails = (TransactionViewDTO) transactionViewManager.getTransactionViewDetails(con, accountNo);
			logger.info("Transaction history for account number=" + transactionViewDetails);
			HttpSession session = request.getSession(false);
			session.setAttribute("transactionViewDetails", transactionViewDetails);
			response.sendRedirect("transactionHistory.jsp");
			/*
				RequestDispatcher rd = getServletContext()
						.getRequestDispatcher("/index.jsp");
				PrintWriter out = response.getWriter();
				logger.error("Transaction not found for account number=" + accountNo);
				//out.println("<font color=red>No user found with given email id, please register first.</font>");
				rd.include(request, response);
			 */
		}catch(BankingException e){
			DatasourceConnectionManager.rollbackConnection(con);
			request.setAttribute("errorMsg", e.getMessage()); //There should be an error block on around the top of every jsp page
			request.getRequestDispatcher("index.jsp").forward(request,response);
		}
	}
}

