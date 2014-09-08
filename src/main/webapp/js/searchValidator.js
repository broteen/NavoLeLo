function validateCustomerName()
{ 
	var customerName= document.getElementById('customerName');
    var customerNameExp= /^[a-zA-Z]+$/;
    if(customerName.value=="")
    	{
    	alert("Customer name cannot be empty");
    	}
    if (customerName.value.match(customerNameExp)) {
		return true;
	} 
    else {
		alert("Customer name invalid. Please enter valid Customer Name.");
		return false;
	}

}
function validateCustomerID()
{ 
	var customerID= document.getElementById('customerID');
    var customerIDExp= /^[0-9]$/;
    if(customeID.value=="")
    	{
    	return true;
    	}
    if (customerID.value.match(customerIDExp)) {
		return true;
	} 
    else {
		alert("Customer id invalid. Please enter valid Customer ID.");
		return false;
	}

}
function validateAccountNo() {

	var accNo = document.getElementById('accNo');
	var accNoExp = /^[0-9]$/;
	if (accNo.value=="") {
		return true;
	}
	if (accNo.value.match(accNoexp)) {
		return true;
	} else {
		alert("Account No invalid. Please enter correct Account Number.");
		return false;
	}
}
function validatePan() {

	var pan = document.getElementById('pan');
	var panExp = /[A-Z]{5}\d{4}[A-Z]/;
	if (pan.value=="") {
		return true;
	}
	if (pan.value.match(panexp)) {
		return true;
	} else {
		alert("PAN No invalid. Please enter correct PAN No.");
		return false;
	}
}

function validateAll(){
    var customerName = document.getElementById('customerName');
    var customerID = document.getElementById('customerID');
    var accNo = document.getElementById('accNo');
    var pan = document.getElementById('pan');
    if((customerName.value=="") && 
       (customerID.value=="") && 
       (accNo.value=="")&&
       (pan.value=="")){
        alert("Please enter atleast one citeria : Name, Account No. or PAN No.");
        document.getElementById('name').focus();
        return false;
    } else {
        return true;
    } 
}