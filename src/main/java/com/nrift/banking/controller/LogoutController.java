package com.nrift.banking.controller;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

/**
 * The Class LogoutController.
 */
@WebServlet(name = "Logout", urlPatterns = { "/logout" })
public class LogoutController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    static Logger logger = Logger.getLogger(LogoutController.class);

    /**
     * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        /*Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("JSESSIONID")){
                    logger.info("JSESSIONID="+cookie.getValue());
                    break;
                }
            }
        }*/
        //invalidate the session if exists
        HttpSession session = request.getSession(false);
        logger.info("User="+session.getAttribute("user"));
        if(session != null){
            logger.info("Destroying the session!");
            session.invalidate();
        }
        response.sendRedirect("login.jsp");
    }

}