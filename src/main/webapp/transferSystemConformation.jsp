<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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
		
 <div id= "content">	
	<form action="transferFund.jsp" method="post">
		<input type="submit" value="OK" />
</div>
</div>
		<div id="footer">
		<%@include file="footer.jsp" %>
		</div>	</form>
</body>
</html>