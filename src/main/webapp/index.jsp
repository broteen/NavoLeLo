<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta charset="US-ASCII">
<title>Home Page</title>
</head>
<body>

	<div id="options" div class="topcorner">

		<form action="logout" method="post">
			Hi,&nbsp;${user.userName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
				type="submit" value="Logout">
		</form>
	</div>


	<div id="navbar">


		<ul id=a>
			<li><a href="index.jsp">Home</a></li>
			<li><a href="">Transactions</a>
				<ul id=b>
					<li><a href="transferFund.jsp">Transfer</a></li>
					<li><a href="withdrawAmt.jsp">Withdraw</a></li>
					<li><a href="deposite.jsp">Deposit</a></li>
				</ul></li>
			<li><a href="Update_details.jsp">Update details</a></li>

		</ul>
		<strong>Your Customer Name is</strong>: ${user.customerDetails.name}<br />

		<strong>Your Last Logged On</strong>: ${user.lastLoggedOn}<br />
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>

		<table border="1" width="30%" cellpadding="5">
			<th>Account No</th>
			<th>Balance</th>
			<th>Account Type</th>

			<c:forEach items="${user.customerDetails.accountList}" var="current">
				<tr>
					<td style="text-align: center; white-space: nowrap"><c:out
							value="${current.accountNo}" /></td>
					<td style="text-align: center; white-space: nowrap"><c:out
							value="${current.balance}" /></td>
					<td style="text-align: center; white-space: nowrap"><c:out
							value="${current.accountType}" /></td>
				</tr>
			</c:forEach>
		</table>


		<table id="footer">
			<tr>
				<td>&copy; Copyright : All Right reserved to NRI FinTech</td>
			</tr>
		</table>
</body>
</html>
