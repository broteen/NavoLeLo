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

		Hi,&nbsp;${user.userName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
			href="login.html">Logout</a>
	</div>


	<div id="navbar">


		<ul id=a>
			<li><a href="index.jsp">Home</a></li>
			<li><a href="">Transactions</a>
				<ul id=b>
					<li><a href="transferFund.jsp">Transfer</a></li>
					<li><a href="Withdraw.jsp">Withdraw</a></li>
					<li><a href="Deposit.jsp">Deposit</a></li>
					<li><a href="searchAccount.jsp">Deposit</a></li>
				</ul></li>
			<li><a href="Update_details.jsp">Update details</a></li>

		</ul>
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
		

		

		<table id="footer">
			<tr>
				<td>&copy; Copyright : All Right reserved to NRI FinTech</td>
			</tr>
		</table>
</body>
</html>
