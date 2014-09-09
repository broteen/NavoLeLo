<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="js/searchValidator.js"></script>
<link href="css\header.css" rel="stylesheet" type="text/css"/>
<link href="css\navbar.css" rel="stylesheet" type="text/css"/>
<link href="css\content.css" rel="stylesheet" type="text/css"/>
<link href="css\footer.css" rel="stylesheet" type="text/css"/>
</head>
<body>

		<div id="header" div class="topcorner">
		
		Hi,&nbsp;${user.userName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="login.html">Logout</a></div>
	

  
 
<ul id=a>
  <li><a href="Home.jsp">Home</a></li>
  <li><a href="">Transactions</a>
  <ul id=b>
                <li><a href="Transfer.jsp">Transfer</a></li>
                <li><a href="Withdraw.jsp">Withdraw</a></li>
                <li><a href="Deposit.jsp">Deposit</a></li>
            </ul></li>
  <li><a href="Update_details.jsp">Update details</a></li>

</ul>


 </div> <!-- end navbar div -->
 <div id= "content" div class="menutitle">
 

					<form method="post" action="searchAccount" name="searchAccount">
						<table width="100%" cellspacing="0" cellpadding="5">
							<tr style="display: none">
								<td colspan="2"><span style="color: red; align: center"></span>
								</td>
							</tr>
							<tr>
								<td>Customer Name*</td>
								<td align="right"><input id="custName" type="text"
									name="customerName" onblur="return validateCustomerName()" /></td>

							</tr>
							
							<tr>
								<td>Customer ID</td>
								<td align="right"><input id="custID" type="text"
									name="customerID" onblur="return validateCustomerID()" /></td>
							</tr>
							<tr>
								<td>Account No</td>
								<td align="right"><input id="accNo" type="text"
									name="accountNo" onblur="return validateAccountNo()" /></td>
							</tr>
							
							
							<tr>
								<td>PAN No</td>
								<td align="right"><input id="pan" type="text" name="panNo"
									onblur="return validatePan()" /></td>
							</tr>

							<tr>
								<td></td>
								<td align="right">
									<input class="button" type="submit" value="Search" onclick="return validateAll()"/>
									<input class="button" type="reset" value="Reset"/>
								</td>
							</tr>
						</table>
					</form>
				</div>
				<div id=footer>Copyright@ NRI Fintech India Pvt Ltd.</div>
</body>
</html>
 