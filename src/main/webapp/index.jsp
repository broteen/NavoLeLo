<!DOCTYPE html>
<html>
<head>
<meta charset="US-ASCII">
<title>Home Page</title>
</head>
<body>
	<h3>Hi ${user.userName}</h3>
	
	<strong>Your Customer Name is</strong>: ${user.customerDetails.name}<br/>
	
	<strong>Your Last Logged On</strong>: ${user.lastLoggedOn}<br/>
	<form action="logout" method="post">
		<input type="submit" value="Logout">
	</form>
</body>
</html>
