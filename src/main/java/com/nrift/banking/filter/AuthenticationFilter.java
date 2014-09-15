/**
 * 
 */
package com.nrift.banking.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.nrift.banking.validators.SessionValidator;


/**
 * The Class AuthenticationFilter.
 */
@WebFilter("/AuthenticationFilter")
public class AuthenticationFilter implements Filter {

	private Logger logger = Logger.getLogger(AuthenticationFilter.class);
	private FilterConfig filterConfig = null;

	/**
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		logger.info("AuthenticationFilter initialized");
	}

	/**
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		String uri = req.getRequestURI();
		logger.info("Requested Resource::"+uri);

		HttpSession session = req.getSession(false);
		filterConfig.getInitParameter("exclude");

		if(SessionValidator.validate(session, req.getRequestURI(), filterConfig.getInitParameter("exclude"))){
			res.setHeader("Cache-Control", "no-cache");
			res.setHeader("Cache-Control", "no-store");
			res.setHeader("Cache-Control", "must-revalidate");
			res.setHeader("Pragma", "no-cache");
			res.setHeader("Expires","0");
			chain.doFilter(request, response);
		}else{
			logger.error("Unauthorized access request");
			res.sendRedirect("login.jsp");
			
		}
	}

	public void destroy() {
		//close any resources here
	}

}
