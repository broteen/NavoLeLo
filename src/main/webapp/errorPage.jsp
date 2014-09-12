<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="US-ASCII">
<title>Error Page</title>
</head>
<body bgcolor="#DCDCDC">
	<br>
	<br>
	<br>
	<div style="background-color: maroon;">
		<h1>
			<font color="white">Oops, an error has occurred! </font>
		</h1>
	</div>
	<c:choose>
		<c:when test="${error.name}!=null">
			<h2>${error.message}</h2>
			<br>
			<br>
			<strong><font size="5">Exception name:</font></strong>
			<span>${error.name}</span>
		</c:when>
		<c:otherwise>
			<h2>${error.customText}</h2>
			<br>
			<strong><font size="5">Error Code: </font></strong>
			<font size="5">${error.statusCode}</font>
			<br>
		</c:otherwise>
	</c:choose>
	<strong><font size="5">The error was encountered while
			trying to retrieve the URL:</font></strong>&nbsp;
	<a href=""><font size="5">${error.requestedUri}</font></a>

</body>
</html>
