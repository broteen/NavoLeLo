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
import com.nrift.banking.dto.AccountDTO;
import com.nrift.banking.dto.TransferAmountDTO;
import com.nrift.banking.dto.UserDTO;
import com.nrift.banking.exception.BankingException;
import com.nrift.banking.service.TransferAuthorizationService;
import com.nrift.banking.utility.DatasourceConnectionManager;

/**
 * Servlet implementation class TransactionAuthorisationController
 */
@WebServlet("/TransactionAuthorisationController")
public class TransferAuthorizationController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	static Logger logger = Logger.getLogger(TransferAuthorizationController.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TransferAuthorizationController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		long senderAccount = Long.parseLong(request.getParameter("senderAccountNumber"));
		long receiverAccount=0L;
		long amount =0L;
		if(request.getParameter("receiverAccountNumber")!="")
			receiverAccount = Long.parseLong(request.getParameter("receiverAccountNumber"));
		if(request.getParameter("amount")!="")
			amount = Long.parseLong(request.getParameter("amount"));
		String errorMsg = null;
		if (senderAccount==0L) {
			errorMsg = "Sender's Account Number can't be null or empty";
		}
		else if (receiverAccount==0L) {
			errorMsg = "Receiver's Account Number can't be null or empty";
		}
		else if(senderAccount==receiverAccount){
			errorMsg = "Sender's and Receiver's Account Number can't be same";
		}
		else if(amount==0L){
			errorMsg = "Amount can't be null or empty or zero";
		}

		if (errorMsg != null) {
			request.setAttribute("errorMsg",errorMsg);
			RequestDispatcher rd = getServletContext().getRequestDispatcher(
					"/transferFund.jsp");
			rd.include(request, response);
		} else {

			Connection con = null;
			try{
				con = DatasourceConnectionManager.getConnection((ComboPooledDataSource)getServletContext().getAttribute(
						"datasource"));
				TransferAuthorizationService trancAuthorise = new TransferAuthorizationService();
				TransferAmountDTO transferAmountDetails= trancAuthorise.validate(con,senderAccount,receiverAccount,amount);
				HttpSession session= request.getSession(false);
				logger.info("Transaction is Authorised");
				session.setAttribute("transferAmountDetails", transferAmountDetails);
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/transferUserConformation.jsp");
				rd.forward(request, response);
				/*
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/transferFund.jsp");
                    PrintWriter out = response.getWriter();
                    logger.error("Transaction is Not Authorised");
                    out.println("<font color=red>Transaction Failed At Authorization</font>");
                    rd.include(request, response);
				 */
			}catch(BankingException e) {
				DatasourceConnectionManager.rollbackConnection(con);
				request.setAttribute("errorMsg", e.getMessage()); //There should be an error block on around the top of every jsp page
				request.getRequestDispatcher("transferFund.jsp").forward(request,response);
			}
		}

	}

}

