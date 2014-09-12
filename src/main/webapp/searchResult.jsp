<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta charset="US-ASCII">
<link href="/header.css" rel="stylesheet" type="text/css"/>
<link href="/content.css" rel="stylesheet" type="text/css"/>
<link href="/footer.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<<<<<<< Upstream, based on master

	<div align="right">
	<div id="header" div class="topcorner">
=======
<div id="error">${errorMsg}</div>
	<div id="options" div class="topcorner">
>>>>>>> 743a215 commit

		Hi,&nbsp;${user.userName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
			href="login.jsp">Logout</a>
	</div>

</div>
	
		<div align="center">
<div id="content">

		<table border="1" width="30%" cellpadding="5">
             <th>Customer Name</th>		
             <th>Customer Id</th>	
			<th>Account No</th>
			<th>Balance</th>
			<th>Account Type</th>

			<c:forEach items="${customerList}" var="customer">
				<tr>
				      <td style="text-align:center;white-space:nowrap" ><c:out value="${customer.name}" /></td>
				     <td style="text-align:center;white-space:nowrap" ><c:out value="${customer.customerId}" /></td>
					<td style="text-align:center;white-space:nowrap" ><c:out value="${customer.accountList[0].accountNo}" /></td>
					<td style="text-align:center;white-space:nowrap" ><c:out value="${customer.accountList[0].balance}" /></td>
					<td style="text-align:center;white-space:nowrap" ><c:out value="${customer.accountList[0].accountType}" /></td>
					<!--  <td  style="text-align:center;white-space:nowrap"><input type="submit"
													style="background-image: url(../images/close.png); color: transparent; display: block; background-repeat: no-repeat; width: 25px; height: 25px;"
													<c:out value="${customer.accountList[0].accountNo}" /> ></input></td>
					-->					
					
				</tr>
			</c:forEach>
		</table>
		
</div>
		
</div>
		<div id="footer">
			<tr>
				<td>&copy; Copyright : All Right reserved to NRI FinTech</td>
			</tr>
		</div>
</body>
</html>
