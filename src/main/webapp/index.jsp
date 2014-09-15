<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta charset="US-ASCII">
<title>Home Page</title>
<link href="css\header.css" rel="stylesheet" type="text/css"/>
<link href="css\navbar.css" rel="stylesheet" type="text/css"/>
<link href="css\content.css" rel="stylesheet" type="text/css"/>
<link href="css\footer.css" rel="stylesheet" type="text/css"/>

</head>
<body>
<div align="right">
	<div id="header" div class="topcorner">
<%@include file="header.jsp" %>
		
	
	</div>
	</div>
<div align="center">
	<div id="navbar">


		<%@include file="navbar.jsp" %>
		</div>
		</div>
		<div align="center">
		<div id="content">
		<br></br>
		<strong> Customer Name is</strong>: ${user.customerDetails.name}<br />

		<strong> Last Logged On</strong>: ${user.lastLoggedOn}<br /><br>
		
		<table border="1" width="30%" cellpadding="5">
			<th>Account No</th>
			<th>Balance</th>
			<th>Account Type</th>

			<c:forEach items="${user.customerDetails.accountList}" var="current">
				<tr>
					<td style="text-align:center;white-space:nowrap" ><c:out value="${current.accountNo}" /></td>
					<td style="text-align:center;white-space:nowrap" ><c:out value="${current.balance}" /></td>
					<td style="text-align:center;white-space:nowrap"><c:out value="${current.accountType}" /></td>
				</tr>
			</c:forEach>
		</table>
<form method="post" action="transactionHistory" name="adminHome">
						<strong>account number</strong>:<input type="text" name="accountNo"/><br/>
<input type="submit" value="View"/> 
		          	</form>
</div>
</div>
		<div id="footer">
		<%@include file="footer.jsp" %>
		</div>
</body>
</html>
