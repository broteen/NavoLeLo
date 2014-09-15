<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">


</head>
<body>
<div align="right">
	<div id="header" div class="topcorner">
<%@include file="header.jsp" %>
		
	
	</div>
	</div>
<div id="error">${errorMsg}</div>
		<div align="center">
		<div id="content">
		<br></br>

	<strong>Account Number  ${AccountClosed}  is  deleted successfully.
	</strong>
	<br />
	<li><a href="delete.jsp">back</a></li>
	<li><a href="index.jsp">Home</a></li>
	</div>
	</div>
	<div id="footer">
		<%@include file="footer.jsp" %>
		</div>
</body>
</html>