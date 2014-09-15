
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Log-in Information</title>
<script type="text/javascript" src="js/loginfoValidation.js"></script>
</head>
<body>
<div id="error">${errorMsg}</div>
	<div align="center">
		<div id="content">		<b><font size="5" face="verdana" color="black">Log-in
				information</font></b> <br> <br> <br> <br>
		<form name=loginfo action="LogInfoController" method="post" onsubmit="return validate()">
			<input type="text" name="customerId" value="${customerId}" style="display:none"></input>
			<table border="1" width="20%" cellpadding="3">
				<tr>
					<td><b>User_name* </b><input type="text" name="username"
						size="40"></td>
				</tr>
				<tr>
					<td><b>Password* </b><input type="text" name="password"
						size="40"></td>
				</tr>
			</table>
			<br> <br> <input type="submit" value="Register"
				name="register" style="height: 30px; width: 100px">

		</form>
	</div>
</div>
		<div id="footer">
		<%@include file="footer.jsp" %>
		</div>
</body>
</html>