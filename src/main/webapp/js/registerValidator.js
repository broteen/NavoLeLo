function validate(){
	
	var name=document.register.name.value;
	var accountNumber=document.register.account_number.value;
	var contactNum=document.register.contact_number.value;
	
	if(name == ''){
		document.getElementById('name').innerHTML="Please Enter Name";
		return false;
	}
	else if(accountNumber == ''){
		document.getElementById('accountNumber').innerHTML="Please Enter Account Number";
		return false;
	}
	else if(contactNum == ''){
		document.getElementById('contactNum').innerHTML="Please Enter Contact Number";
		return false;
	}
	if(accountNumber.length > 10){
		document.getElementById('accountNumber').innerHTML="Account Number is too long. Username should be less than 20 characters";
		return false;
	}
	if(contactNum.length > 10){
		document.getElementById('contactNum').innerHTML="Contact Number is too long. Password should be less than 20 characters";
		return false;
	}
	
	if (!document.register.name.value.match(/^[a-zA-Z]+$/)){
         document.getElementById('name').innerHTML="Please Enter only alphabets in text";
         return false;
    }	
	else
		return true;
}