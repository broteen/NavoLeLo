<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css\header.css" rel="stylesheet" type="text/css"/>
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
		<div id="content">
		<br></br>
	<div id="error">${errorMsg}</div>
	${requestScope.message}
	<form action="deposite.jsp" method="post">
		<input type="submit" value="OK" />
	</form>
</div>
		</div>
	<div id="footer">
		<%@include file="footer.jsp" %>
		</div>
</body>
</html>