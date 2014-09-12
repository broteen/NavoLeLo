
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration Page</title>
<script type="text/javascript" src="js/registerValidator.js"></script>
</head>
<body>
<div id="error"></div>
	<center>
		<b><font size="6" face="verdana" color="black">Registration
				Page</font></b> <br> <br>
		<form name=register action="RegisterController" method="post" onsubmit="return validate()">


			<br> <br>
			<table border="1" width="50%" cellpadding="3">
				<thead>
					<tr>
						<th colspan="2">Register Here</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><b>Customer Name* </b></td>
						<td><input type="text" name="name" size="40" /></td>
					<tr>
						<td><b>Account Number* </b></td>
						<td><input type="text" name="account_number" size="40"></td>
					<tr>
						<td><b>Contact Number* </b></td>
						<td><input type="text" name="contact_number" size="40"></td>
				</tbody>
			</table>
			<br> <br> <input type="submit" size="50" value="Next"
				name="next" style="height: 40px; width: 80px"> <input
				type="reset" size="50" value="Reset" name="reset"
				style="height: 40px; width: 80px"> <br> <br>
		</form>
		Fields marked with (*) are mandatory
	</center>

</body>
</html>