<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div id="error">${errorMsg}</div>
	<form action="TransferAmountController" method="post">
		<table>
			<tr>
				<td>Your Account Number</td>
				<td>${transferAmountDetails.senderAccountNo}</td>
			</tr>
			<tr>
				<td>Receiver's Account Number</td>
				<td>${transferAmountDetails.receiverAccountNo}</td>
			</tr>
			<tr>
				<td>Amount</td>
				<td>${transferAmountDetails.amount}</td>
			</tr>
			<tr>
				<td><input type="submit" value="Confirm" /></td>
				<td><input type="button" onclick="history.go(-1);" value="Back" /></td>
			</tr>
		</table>
	</form>
</html>