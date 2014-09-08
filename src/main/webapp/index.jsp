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
		
		Hi,&nbsp;${user.userName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="login.html">Logout</a></div>
	

 <div id="navbar">
  
 
<ul id=a>
  <li><a href="index.jsp">Home</a></li>
  <li><a href="">Transactions</a>
  <ul id=b>
                <li><a href="transferFund.jsp">Transfer</a></li>
                <li><a href="Withdraw.jsp">Withdraw</a></li>
                <li><a href="Deposit.jsp">Deposit</a></li>
            </ul></li>
  <li><a href="Update_details.jsp">Update details</a></li>

</ul>
	
	<table>
      <th>Account No</th>
      <th>Balance</th>
      <th>Account Type</th>
       <th>Last Transaction Date</th>
      
      <c:forEach items="${user.customerDetails.accountList}" var="current">
        <tr>
          <td><c:out value="${current.accountNo}" /><td>
          <td><c:out value="${current.balance}" /><td>
          <td><c:out value="${current.accountType}" /><td>
        </tr>
      </c:forEach>
    </table>
	<strong>Your Customer Name is</strong>: ${user.customerDetails.name}<br/>
	
	<strong>Your Last Logged On</strong>: ${user.lastLoggedOn}<br/>
	
	
	
	
	
</body>
</html>
