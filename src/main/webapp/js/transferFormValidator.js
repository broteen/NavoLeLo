function validate(){
	
	var receiverAccountNumber=document.transferFund.receiverAccountNumber.value;
	var amount=document.transferFund.amount.value;
	
	
	if(receiverAccountNumber == ''){
		document.getElementById('error').innerHTML="Please Enter correct Account Number";
		return false;
	}
	else if(amount == ''){
		document.getElementById('content').innerHTML="Please Enter the amount to be transferred";
		return false;
	}
	if(amount.length > 10){
		document.getElementById('error').innerHTML="Please enter amount in INR";
		return false;
	}
	else if(receiverAccountNumber.length > 10){
		document.getElementById('error').innerHTML="Account Number is too long";
		return false;
	}
	if (!receiverAccountNumber.match(/^[0-9]/)){
        document.getElementById('error').innerHTML="Please enter numeric value for Account Number";
        return false;
   }	
	
	else if (!amount.match(/^[0-9]/)){
         document.getElementById('error').innerHTML="Please enter numeric value for amount";
         return false;
    }	
	else
		return true;
}