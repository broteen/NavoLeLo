function validate(){

	var amount=document.withdrawAmt.Amount.value;

	if(amount == ''){
		document.getElementById('error').innerHTML="Please Enter the amount to be withdrawn";
		return false;
	}
	if(amount.length > 10){
		document.getElementById('error').innerHTML="Please enter amount in INR";
		return false;
	}

	if (!amount.match(/^[0-9]/)){
		document.getElementById('error').innerHTML="Please enter numeric value for amount";
		return false;
	}	
	else
		return true;
}