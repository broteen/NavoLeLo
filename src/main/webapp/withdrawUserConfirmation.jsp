<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Withdrawal Confirmation</title>
</head>
<body>
	<h3>Please confirm the details to proceed with the transaction:</h3>
	<form name="withdrawUserConfirmation" action="WithdrawAmountController"
		method="post">
		<strong>Account Number: </strong>${withdrawAmountDetails.accountNo}<br />
		<strong>Amount: </strong>${withdrawAmountDetails.amount}<br /> <input
			type="submit" value="Confirm" /> <input type="button" value="Back" />
	</form>
</body>
</html>