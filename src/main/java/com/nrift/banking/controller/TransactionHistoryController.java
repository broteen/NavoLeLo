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

import com.nrift.banking.dto.TransactionHistoryDTO;
import com.nrift.banking.dto.TransactionViewDTO;
import com.nrift.banking.exception.BankingException;
import com.nrift.banking.service.TransactionHistoryService;
import com.nrift.banking.service.TransactionViewService;

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

		Connection con = (Connection) getServletContext().getAttribute(
				"connection");
		TransactionViewService transactionViewManager = new TransactionViewService();

		try{
			TransactionViewDTO transactionViewDetails = (TransactionViewDTO) transactionViewManager.getTransactionViewDetails(con, accountNo);
			logger.info("Transaction history for account number=" + transactionViewDetails);
			HttpSession session = request.getSession();
			request.setAttribute("transactionHistory", transactionViewDetails);
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
			try {
				con.rollback();
				logger.error(" Exception Thrown"+e.getMessage());
			} catch(SQLException e1) {
				logger.error("Rollback error=" + e1.getMessage());
			}
			request.setAttribute("errorMsg", e.getMessage()); //There should be an error block on around the top of every jsp page
			request.getRequestDispatcher("searchAccount.jsp").forward(request,response);
		}
	}
}

