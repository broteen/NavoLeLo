<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="US-ASCII">
<title>Home Page</title>


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
		<div id="error">${errorMsg}</div>
		
 <div id= "content" div class="menutitle">
 <form id="accountDetails" name="accountDetails" method="post" action="">
	  <table width="80%" border="0" align="center" cellpadding="2" cellspacing="2">
        <p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>	


          <strong>Account Number</strong>:${transactionViewDetails.accountNo}</br>
        
        
           <strong>Account Type</strong>:${transactionViewDetails.accountType}</br>
      
        
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
        
<c:forEach items="${transactionViewDetails.transactionHistoryDetailsList}" var="current">
        <tr>
         <td style="text-align:center;white-space:nowrap"><c:out value="${current.transactionRef}" /></strong></div></td>
       <td style="text-align:center;white-space:nowrap"><c:out value="${current.transactionTime}" /></strong></div></td>
       <td style="text-align:center;white-space:nowrap"><c:out value="${current.crAccNum}" /></strong></div></td>
            <td style="text-align:center;white-space:nowrap"><c:out value="${current.drAccNum}" /></strong></div></td>
               <td style="text-align:center;white-space:nowrap"><c:out value="${current.amount}" /></strong></div></td>
       
       </tr>
        </c:forEach>
        </tbody>
       </table>
      
        
         <strong>Balance</strong>:${transactionViewDetails.balance}</br>
       
 </div>
 </div>
 	<div id="footer">
		<%@include file="footer.jsp" %>
		</div>
</body>
</html>