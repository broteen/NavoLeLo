<html>
<head>
<meta charset="US-ASCII">
<title>Login Page</title>
<script type="text/javascript" src="js/loginValidator.js"></script>
</head>
<body>
	<div id="error">${errorMsg}</div>
	<div id="error">${errorMsg}</div>
	<h3>Login with email and password</h3>
	<form name="login" action="login" method="post"
		onsubmit="return validate()">
		<strong>User Name</strong>:<input type="text" name="username" /><br />
		<strong>Password</strong>:<input type="password" name="password" /><br />
		<input type="submit" value="Login" /> <input type="reset"
			value="Reset" />
	</form>

	<br /> If you are new user, please
	<a href="register.jsp">register</a>

</body>
</html>