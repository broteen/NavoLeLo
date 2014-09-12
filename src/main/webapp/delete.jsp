<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delete Account Page</title>
<script type="text/javascript" src="js/closeAccountValidator.js"></script>
</head>
<body>
	<div id="error">${errorMsg}</div>
	<h3>Enter account number to be deleted  </h3>
	<form name="deleteAccountNumber" action="CloseAccountController"
		method="post">
		<strong>Account Number</strong>:<input type="text" name="accountNo" /><br />
		<input type="submit" value="Submit" onClick="return closeConfirm()">
		<input type="reset" value="Reset" />
	</form>
</body>
</html>