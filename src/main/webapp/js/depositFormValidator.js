function validate(){
	
	var accountNumber=document.deposite.accountNumber.value;
	var amount=document.deposite.amount.value;
	
	if(amount == ''){
		document.getElementById('content').innerHTML="Please Enter the amount to be deposite";
		return false;
	}
	else if(accountNumber == ''){
		document.getElementById('content').innerHTML="Please Enter the account Number";
		return false;
	}
	if(amount.length > 10){
		document.getElementById('content').innerHTML="Please enter amount in INR";
		return false;
	}
	else if(accountNumber.length > 10){
		document.getElementById('content').innerHTML="Please enter correct Account Number";
		return false;
	}
	
	if (!amount.match(/^[0-9]/)){
         document.getElementById('content').innerHTML="Please enter numeric value for amount";
         return false;
    }	
	if (!accountNumber.match(/^[0-9]/)){
        document.getElementById('content').innerHTML="Please enter numeric value for amount";
        return false;
   }	
	else
		return true;
}