<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Log-in Information</title>
</head>
<body>
<center>
<b><font size="5" face="verdana" color="black">Log-in information</font></b>
<br>
<br>
 <textarea style="border: 0px solid #000000;"> </textarea>
<br>
<br>
<form action= "loginsuccess.jsp" method="post">
<table  border="1" width="20%" cellpadding="3">
<tr><td><b>User_Id* </b><input type="text" name="username" size="40"></td></tr>
  <tr><td><b>Password*   </b><input type="text" name="account_number" size="40"></td></tr>
  </table>
  <br>
  <br>
  <input type="submit" value="Register" name="register" style="height:30px; width:100px">
  
  </form>
</center>
</body>
</html>