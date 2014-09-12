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

import com.nrift.banking.dto.TransferAmountDTO;
import com.nrift.banking.dto.UserDTO;
import com.nrift.banking.service.DepositeConfirmService;
import com.nrift.banking.utility.UserInstantiation;

/**
 * The Class DepositeConfirmController.
 */
@WebServlet(name = "DepositeConfirm", urlPatterns = { "/DepositeConfirmController" })
public class DepositeConfirmController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    static Logger logger = Logger.getLogger(DepositeConfirmController.class);

    /**
     * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Inside Deposite Controller Class");
        Connection con = (Connection) getServletContext().getAttribute("connection");
        DepositeConfirmService depositeConfirm = new DepositeConfirmService ();
        HttpSession session= request.getSession(false);
        UserDTO user = (UserDTO)session.getAttribute("user"); 
        TransferAmountDTO depositeAmountDetails = (TransferAmountDTO)session.getAttribute("depositeAmountDetails");
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/depositeSuccessReport.jsp");

        try{

            if (depositeConfirm.IsDeposited(con,depositeAmountDetails.getReceiverAccountNo(), depositeAmountDetails.getAmount(),user.getUserId())) {
                {
                	user.setCustomerDetails(UserInstantiation.getCustomerDetails(con, user.getUserId()));
                    session.setAttribute("user", user);
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
