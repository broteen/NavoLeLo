<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

</head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <script type="text/javascript" >
 var validPassword = function(){
		if(document.changePswd.currentPswd.value == "")
		{
		alert('Please input old password');
		document.changePswd.currentPswd.focus();
		return false;
		} 

		if(document.changePswd.newPswd.value == "")
		{
		alert('Please input Password');
		document.changePswd.newPswd.focus(); 
		return false;
		} 

		if(document.changePswd.confirmPswd.value == "")
		{
		alert('Please input Confirm Password');
		document.changePswd.confirmPswd.focus(); 
		return false;
		} 

		if(document.changePswd.newPswd.value != document.changePswd.confirmPswd.value)
		{
		alert('Confirm Password Not Match');
		document.changePswd.confirmPswd.focus(); 
		return false;
		}

};
 </script>
<title>Change Password</title>
<div id="error">${errorMsg}</div>
<h1>Change Password</h1>
<form name="changePswd" method="post" action="ChangePasswordController">
<table>
<tr><td>Current Password</td><td><input type="password" name="currentPswd" ></td></tr>
<tr><td>New Password</td><td><input type="password" name="newPswd"></td></tr>
<tr><td>Confirm Password</td><td><input type="password" name="confirmPswd"></td></tr>
<tr><td><input type="submit" value="Change Password" onClick="return validPassword()" ></td></tr>
</table>
</form>
<body>
</body>
</html>