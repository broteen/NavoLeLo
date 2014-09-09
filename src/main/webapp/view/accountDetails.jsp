<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css\header.css" rel="stylesheet" type="text/css"/>
<link href="css\navbar.css" rel="stylesheet" type="text/css"/>
<link href="css\content.css" rel="stylesheet" type="text/css"/>
<link href="css\footer.css" rel="stylesheet" type="text/css"/>
</head>
<body>

		<div id="header" div class="topcorner">
		
		Hi,&nbsp;${user.userName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="login.html">Logout</a></div>
	

 <div id="navbar">
  
 
<ul id=a>
  <li><a href="Home.jsp">Home</a></li>
  <li><a href="">Transactions</a>
  <ul id=b>
                <li><a href="Transfer.jsp">Transfer</a></li>
                <li><a href="Withdraw.jsp">Withdraw</a></li>
                <li><a href="Deposit.jsp">Deposit</a></li>
            </ul></li>
  <li><a href="Update_details.jsp">Update details</a></li>

</ul>


 </div> <!-- end navbar div -->
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
          <td><div align="justify">${user.customerDetails.getAccountDetails.accountNo}</div></td>
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
        
<c:forEach items="${user.customerDetails.accountList.transactionHistoryList}" var="current">
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
 </div>
 <div id=footer>Copyright@ NRI Fintech India Pvt Ltd.</div>
</body>
</html>