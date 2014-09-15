package com.nrift.banking.controller;


import java.io.IOException;
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
import com.nrift.banking.dto.UserDTO;
import com.nrift.banking.exception.BankingException;
import com.nrift.banking.service.DepositeConfirmService;
import com.nrift.banking.utility.DatasourceConnectionManager;
import com.nrift.banking.utility.UserInstantiation;

/**
 * The Class DepositeConfirmController.
 */
@WebServlet(name = "DepositeConfirm", urlPatterns = { "/DepositeConfirmController" })
public class DepositConfirmController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static Logger logger = Logger.getLogger(DepositConfirmController.class);

	/**
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Inside Deposite Controller Class");
		DepositeConfirmService depositeConfirm = new DepositeConfirmService ();
		HttpSession session= request.getSession(false);
		UserDTO user = (UserDTO)session.getAttribute("user"); 
		TransferAmountDTO depositeAmountDetails = (TransferAmountDTO)session.getAttribute("depositAmountDetails");
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/depositSuccessReport.jsp");
		Connection con=null;
		try{
			con = DatasourceConnectionManager.getConnection((ComboPooledDataSource)getServletContext().getAttribute("datasource"));
			depositeConfirm.makeDeposit(con,depositeAmountDetails.getReceiverAccountNo(), depositeAmountDetails.getAmount(),user.getUserId());
			user.setCustomerDetails(UserInstantiation.getCustomerDetails(con, user.getUserId()));
			session.setAttribute("user", user);
			logger.info("Deposit is Successfull");
			request.setAttribute("message", "Deposite is Successfull");
			rd.forward(request, response);
		}catch(BankingException e) {
			DatasourceConnectionManager.rollbackConnection(con);
			request.setAttribute("errorMsg", e.getMessage()); //There should be an error block on around the top of every jsp page 
			request.getRequestDispatcher("confirmDeposit.jsp").forward(request,response);
		}
	}

}
