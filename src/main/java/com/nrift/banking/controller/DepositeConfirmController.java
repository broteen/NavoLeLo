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

import com.nrift.banking.utility.DepositeConfirmService;
import com.nrift.banking.utility.TransferAmountDTO;
import com.nrift.banking.utility.UserDetails;

@WebServlet(name = "DepositeConfirm", urlPatterns = { "/DepositeConfirmController" })
public class DepositeConfirmController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static Logger logger = Logger.getLogger(DepositeConfirmController.class);
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Inside Deposite Controller Class");
		Connection con = (Connection) getServletContext().getAttribute("connection");
		DepositeConfirmService depositeConfirm = new DepositeConfirmService ();
		HttpSession session= request.getSession(false);
		UserDetails user = (UserDetails)session.getAttribute("user"); 
		TransferAmountDTO depositeAmountDetails = (TransferAmountDTO)session.getAttribute("depositeAmountDetails");
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/depositeSuccessReport.jsp");
				
		try{
			
			if (depositeConfirm.IsDeposited(con,depositeAmountDetails.getReceiverAccountNo(), depositeAmountDetails.getAmount(),user.getUserId())) {
				{
				logger.info("Deposite is Successfull");
				request.setAttribute("message", "Deposite is Successfull");
				}
				
			} else {
				con.rollback();
				logger.error("Deposite is Not Successfull...Rollling Back");
				request.setAttribute("message", "Sorry!!! Deposite is Not Successfull");
			}
			rd.forward(request, response);
		}catch(SQLException |ServletException| IOException e) {
			try {
                con.rollback();
            } catch(SQLException e1) {
                logger.error("Rollback error");
            }
			logger.error(" Exception Thrown");
			//There should be an error block on around the top of every jsp page
			request.setAttribute("errorMsg", "Exception Occured!");
            request.getRequestDispatcher("confirmDeposit.jsp").forward(request,response);
		}
	}

}
