<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!--  <script type="text/javascript" src="js/searchValidator.js"></script> -->

</head>
<body>

		

					<form method="post" action="searchAccountController" name="searchAccount">
						<strong>Customer Name</strong>:<input type="text" name="customerName"/><br/>
						<strong>Customer ID</strong>:<input type="text" name="customerId" /><br/>
						<strong>Account No</strong>:<input type="text" name="accountNo" /><br/>
						<strong>PAN No</strong>:<input type="text" name="panNo"/><br/>	
								
					<input type="submit" value="Search"/> 
		          	</form>
				
</body>
</html>
 