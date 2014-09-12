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

import com.nrift.banking.dto.UserDTO;
import com.nrift.banking.dto.WithdrawAmountDTO;
import com.nrift.banking.exception.BankingException;
import com.nrift.banking.service.WithdrawAmountService;
import com.nrift.banking.utility.UserInstantiation;

/**
 * Servlet implementation class withdrawAmountController
 */
@WebServlet("/WithdrawAmountController")
public class WithdrawAmountController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    static Logger logger = Logger
            .getLogger(WithdrawalAuthorizationController.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public WithdrawAmountController() {
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
        // TODO Auto-generated method stub

        Connection con = (Connection) getServletContext().getAttribute("connection");
        WithdrawAmountService withdrawAmount = new WithdrawAmountService();
        HttpSession session = request.getSession(false);
        UserDTO user = (UserDTO)session.getAttribute("user");
        WithdrawAmountDTO withdrawAmountDetails = (WithdrawAmountDTO) session
                .getAttribute("withdrawAmountDetails");
        RequestDispatcher rd = getServletContext().getRequestDispatcher(
                "/withdrawSystemConfirmation.jsp");

        try {
        	withdrawAmount.makeWithdraw(con, withdrawAmountDetails, user.getUserId()); 
            	user.setCustomerDetails(UserInstantiation.getCustomerDetails(con, user.getUserId()));
                session.setAttribute("user", user);
                session.removeAttribute("transferAmountDetails");
                logger.info("Withdraw Successfull");
                request.setAttribute("message", "Amount has been dispatched");

            rd.forward(request, response);
        } catch(BankingException e) {
            try {
                con.rollback();
                logger.error(" Exception Thrown="+e.getMessage());
            } catch(SQLException e1) {
                logger.error("Rollback error="+e1.getMessage());
            }
            request.setAttribute("errorMsg",e.getMessage()); //There should be an error block on around the top of every jsp page
            request.getRequestDispatcher("withdrawAmt.jsp").forward(request,response);
        }
    }

}
