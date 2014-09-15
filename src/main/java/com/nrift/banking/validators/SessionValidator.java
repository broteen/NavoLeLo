package com.nrift.banking.validators;

import javax.servlet.http.HttpSession;

public class SessionValidator {
	
	/**
	 * Validates the current request url.
	 *
	 * @param session the {@link HttpSession}
	 * @param requestUrl the request url
	 * @param excludeString the exclude string
	 * @return true, if successful
	 */
	public static boolean validate(HttpSession session, String requestUrl, String excludeString) {
		boolean isValidSession = true;
		boolean isToBeExcluded = false;
		if(session == null || session.getAttribute("user") == null) {
			isValidSession = false;
		}
		String[] excludePatterns = excludeString.split(",");
		for (String pattern : excludePatterns) {
			if(requestUrl.endsWith(pattern)) {
				isToBeExcluded = true;
				break;
			}
		}

		if(isToBeExcluded){
			return true;
		} else {
			if(isValidSession) {
				return true;
			} else {
				return false;
			}
		}

	}
}
