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

import com.nrift.banking.dto.TransferAmountDTO;
import com.nrift.banking.dto.UserDTO;
import com.nrift.banking.exception.BankingException;
import com.nrift.banking.service.TransferAmountService;
import com.nrift.banking.service.TransferAuthorizationService;
import com.nrift.banking.utility.UserInstantiation;
/**
 * Servlet implementation class TransferAmountController
 */
@WebServlet("/TransferAmountController")
public class TransferAmountController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static Logger logger = Logger.getLogger(TransferAuthorizationController.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TransferAmountController() {
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

		Connection con = (Connection) getServletContext().getAttribute("connection");
		TransferAmountService transAmount = new TransferAmountService();
		HttpSession session= request.getSession(false);
		TransferAmountDTO transferAmountDetails = (TransferAmountDTO)session.getAttribute("transferAmountDetails"); 
		UserDTO user = (UserDTO)session.getAttribute("user"); 
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/transferSystemConformation.jsp");

		try{
			transAmount.makeTransfer(con,transferAmountDetails,user.getUserId()); 
			user.setCustomerDetails(UserInstantiation.getCustomerDetails(con, user.getUserId()));
			session.setAttribute("user", user);
			session.removeAttribute("transferAmountDetails");
			logger.info("Transaction is Successfull");
			request.setAttribute("message", "Transaction is Successfull");
			rd.forward(request, response);
		}catch(BankingException e) {
			try {
				con.rollback();
				logger.error(" Exception Thrown"+e.getMessage());
			} catch(SQLException e1) {
				logger.error("Rollback error=" + e1.getMessage());
			}
			request.setAttribute("errorMsg",e.getMessage()); //There should be an error block on around the top of every jsp page
			request.getRequestDispatcher("transferFund.jsp").forward(request,response);
		}
	}

}
