<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="US-ASCII">
<title>Error Page</title>
</head>
<body>
	<h1>
		<font color="red">Oops, an error has occured! </font>
	</h1>
	<c:choose>
		<c:when test="${error.name}!=null">
			<strong><font size="4">Exception name:</font></strong>
			<span>${error.name}</span>
			<br>
			<br>
			<strong><font size="4">Exception message:</font></strong>
			<span>${error.message}</span>
		</c:when>
		<c:otherwise>
			<strong><font size="4">Error Code: </font></strong>
			<span>${error.statusCode}</span>
			<br>
			<br>
			<strong><font size="4">Error Description: </font></strong>
			<span>${error.customText}</span>
		</c:otherwise>
	</c:choose>

	<br>
	<br>
	<strong><font size="4">Requested URI:</font></strong>
	<span>${error.requestedUri}</span>
	<div></div>
</body>
</html>
