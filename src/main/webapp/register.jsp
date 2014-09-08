<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration Page</title>
</head>
<body>
<center>
<b><font size="6" face="verdana" color="black">Registration Page</font></b>
<br>
<br>
  <form action= "loginfo.jsp" method="post">
  
  <textarea style="border: 0px solid #000000;"> </textarea>
  <br>
  <br>
  <table border="1" width="30%" cellpadding="3">
                <thead>
                    <tr>
                        <th colspan="2">Register Here</th>
                    </tr>
                </thead>
                <tbody>
  <tr><td><b>Customer Name* </b><input type="text" name="name" size="60"/></td>
 
  <tr><td><b>Account Number* </b><input type="text" name="account_number" size="60"></td>
  <tr><td><b>Contact Number* </b><input type="text" name="contact_number" size="60"></td>
<tr><td><b>Pan Card Number </b><input type="text" name="pan_number" size="60"></td>
  
  </tbody>
 </table>
 <br>
 <br>
  <input type="submit" size="50" value="Next" name="next" style="height:40px; width:80px">
  <input type="reset" size="50" value="Reset" name="reset" style="height:40px; width:80px">
 <br>
 <br>
 </form>
 Fields marked with (*) are mandatory
  </center>
  
</body>
</html>