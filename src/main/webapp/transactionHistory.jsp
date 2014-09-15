<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<div id="error">${errorMsg}</div>
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
		
 <div id= "content" div class="menutitle">
 <form id="accountDetails" name="accountDetails" method="post" action="">
	  <table width="80%" border="0" align="center" cellpadding="2" cellspacing="2">
        <p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>	
<thead>
<tr>
          <td><div align="center"><strong>Account Number:</strong></div></td>
          <td><div align="justify">${user.customerDetails.transactionViewDetails.getAccountDetails.accountNo}</div></td>
        </tr>
        <tr>
        <td><div align="center"><strong>Account Type:</strong></div></td>
          <td><div align="justify">${user.customerDetails.transactionViewDetails.getAccountDetails.accountType}</div></td>
        </tr>
        </thead>
        <table border=1>
 <thead id="must_be_static">

       <tr>
          <th>Transaction_Ref</th>
          <th>Transaction_Time</th>
          <th>Cr_AccNum</th>
          <th>Dr_AccNum</th>
          <th>Amount</th>
       </tr>
    </thead>
        <tbody id="table" align="center">
        
<c:forEach items="${user.customerDetails.transactionViewDetails.transactionHistoryDetailsList}" var="current">
        <tr>
         <td><div align="center"><c:out value="${current.transactionRef" /></strong></div></td>
        <td><div align="center"><c:out value="${current.transactionTime}" /></strong></div></td>
       <td><div align="center"><c:out value="${current.crAccNum}" /></strong></div></td>
               <td><div align="center"><c:out value="${current.drAccNum}" /></strong></div></td>
               <td><div align="center"><c:out value="${current.amount}" /></strong></div></td>
       
       </tr>
        </c:forEach>
        </tbody>
       </table>
       <tr>
        <td><div align="center"><strong>Account Type:</strong></div></td>
          <td><div align="justify">${user.customerDetails.transactionViewDetails.getAccountDetails.balance}</div></td>
        </tr>
 </div>
 </div>
 	<div id="footer">
		<%@include file="footer.jsp" %>
		</div>
</body>
</html>