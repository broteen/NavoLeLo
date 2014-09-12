function validate(){
	
	var name=document.register.name.value;
	var accountNumber=document.register.account_number.value;
	var contactNum=document.register.contact_number.value;
	
	if(name == ''){
		document.getElementById('error').innerHTML="Please Enter Name";
		return false;
	}
	else if(accountNumber == ''){
		document.getElementById('error').innerHTML="Please Enter Account Number";
		return false;
	}
	else if(contactNum == ''){
		document.getElementById('error').innerHTML="Please Enter Contact Number";
		return false;
	}
	if(accountNumber.length > 10){
		document.getElementById('error').innerHTML="Account Number is too long. ";
		return false;
	}
	if(contactNum.length > 10){
		document.getElementById('error').innerHTML="Contact Number is too long. Account Number should be of 10 characters";
		return false;
	}
	
/*	if (!name.match(/^[a-zA-Z]+$/)){
         document.getElementById('error').innerHTML="Please Enter only alphabets in text";
         return false; 
    }	*/
	if(isDigits(accountNumber)==false){
		document.getElementById('error').innerHTML="Account Number should contain Numeric values";
		return false;
	}
	else if(isDigits(contactNum)==false){
		document.getElementById('error').innerHTML="Contact Number should contain Numeric values";
		return false;
	}
	else
		return true;
}


function isDigits(argvalue) {
    argvalue = argvalue.toString();
    var validChars = "0123456789";
    var startFrom = 0;
    if (argvalue.substring(0, 2) == "0x") {
       validChars = "0123456789abcdefABCDEF";
       startFrom = 2;
    } else if (argvalue.charAt(0) == "0") {
       validChars = "01234567";
       startFrom = 1;
    }
    for (var n = 0; n < argvalue.length; n++) {
        if (validChars.indexOf(argvalue.substring(n, n+1)) == -1) return false;
    }
  return true;
}