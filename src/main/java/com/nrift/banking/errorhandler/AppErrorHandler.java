/**
 * 
 */
package com.nrift.banking.errorhandler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.nrift.banking.controller.LoginController;
import com.nrift.banking.utility.AppErrorDTO;

/**
 * @author zeeshank
 * 
 */
@WebServlet("/AppErrorHandler")
public class AppErrorHandler extends HttpServlet {
    private static final long serialVersionUID = 1L;
    static Logger logger = Logger.getLogger(LoginController.class);

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        processError(request, response);
    }

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        processError(request, response);
    }

    private void processError(HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
        // Analyze the servlet exception
        Throwable throwable = (Throwable) request
                .getAttribute("javax.servlet.error.exception");
        Integer statusCode = (Integer) request
                .getAttribute("javax.servlet.error.status_code");
        String servletName = (String) request
                .getAttribute("javax.servlet.error.servlet_name");
        if (servletName == null) {
            servletName = "Unknown";
        }
        String requestUri = (String) request
                .getAttribute("javax.servlet.error.request_uri");
        if (requestUri == null) {
            requestUri = "Unknown";
        }
        AppErrorDTO appError = new AppErrorDTO();



        if (throwable != null) {
            appError.setMessage(throwable.getMessage());
            appError.setName(throwable.getClass().getName());
        } else {
            appError.setStatusCode(statusCode);
            if (statusCode == 404) {
                appError.setCustomText("Page Not Found");
            } else if (statusCode == 403) {
                appError.setCustomText("Access Denied");
            } else if (statusCode == 500) {
                appError.setCustomText("Internal Server Error");
            } else {
                appError.setCustomText("Unknown error");
            }
        }

        appError.setRequestedUri(requestUri);
        request.setAttribute("error", appError);

        response.setContentType("text/html");
        request.getRequestDispatcher("/errorPage.jsp").forward(request,
                response);

    }

}
