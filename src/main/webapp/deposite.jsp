<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html><head>		<link href="css\header.css" rel="stylesheet" type="text/css"/>
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
<div id="error">${errorMsg}</div>
		<div align="center">
		<div id="content">
		<br></br>			<form action="DepositeController" method="post">
					<br> <br>
					<table border="1" width="30%" cellpadding="3">
						<thead>
							<tr>
								<th colspan="2"><h3>Please fill the Details for
										Depositing</h3></th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td><b>Account Number</b><input type="text"
									name="accountNumber" size="40" /></td>
							<tr>
								<td><b>Amount(INR) </b><input type="text" name="amount"
									size="40"></td>
							</tr>
						</tbody>
					</table>
					<input type="submit" size="50" value="Next" name="next"
						style="height: 40px; width: 80px"> <input type="reset"
						size="50" value="Reset" name="reset"
						style="height: 40px; width: 80px"> <br> <br>
				</form>
	</div>
</div>
		<div id="footer">
		<%@include file="footer.jsp" %>
		</div>

</body>

</html>