<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Withdraw Money</title>
<script type="text/javascript" src="js/withdrawFormValidator.js"></script>
</head>
<body>
	<div id="error">${errorMsg}</div>
	<h3>Fill in the account number and amount to proceed:</h3>
	<form name="withdrawAmt" action="WithdrawalAuthorizationController"
		method="post" onsubmit="return validate()">
		<strong>Account Number:</strong> <select name="AccountNumber">
			<c:forEach var="account" items="${user.customerDetails.accountList}">
				<option value="${account.accountNo}">${account.accountNo}</option>
			</c:forEach>
		</select> <br /> <strong>Amount:</strong><input type="text" name="Amount" /><br />
		<input type="submit" value="Withdraw" /> <input type="reset"
			value="Reset" />
	</form>
</body>
</html>