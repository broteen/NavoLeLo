<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="js/loginValidator.js"></script>


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
<div id="error">${errorMsg}</div>

		<div align="center">
		
 <div id= "content">
	

	<form name="transferFund" action="TransactionAuthorisationController"
		method="post" onsubmit="return validate()">
		<strong>Sender's Account Number</strong>: <select
			name="senderAccountNumber">
			<c:forEach var="account" items="${user.customerDetails.accountList}">
				<option value="${account.accountNo}">${account.accountNo}</option>
			</c:forEach>
		</select> <br /> <strong>Receiver's Account Number</strong>:<input type="text"
			name="receiverAccountNumber"
			<c:out value="${receiverAccountNo.receiverAccountNo}" /> /><br /> <strong>Amount</strong>:<input
			type="text" name="amount"
			<c:out value="${receiverAccountNo.amount}" /> /><br /> <input
			type="submit" value="Submit" /> <input type="reset" value="Reset" />
	</form>
	        </tr>
 </div>
 </div>
 	<div id="footer">
		<%@include file="footer.jsp" %>
		</div>
</body>
</html>
