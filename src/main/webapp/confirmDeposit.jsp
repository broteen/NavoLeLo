<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

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
		<div id="content">
		<br></br>			<form action="DepositeConfirmController" method="post">
					<br> <br>
					<table border="1" width="30%" cellpadding="3">
						<thead>
							<tr>
								<th colspan="2"><h3>
										Please<b> Confirm </b>the Details for Depositing
									</h3></th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td><b>Account Number </b></td>
								<td>${depositAmountDetails.receiverAccountNo}</td>
							</tr>

							<tr>
								<td><b>Amount(INR) </b></td>
								<td>${depositAmountDetails.amount}</td>
							</tr>
						</tbody>
					</table>
					<input type="submit" size="50" value="Confirm" name="next"
						style="height: 40px; width: 80px"> <input type="button"
						onclick="history.go(-1);" value="Back"
						style="height: 40px; width: 80px" /> <br> <br>
				</form>
			</div>
</div>
		<div id="footer">
		<%@include file="footer.jsp" %>
		</div>

</body>
</html>