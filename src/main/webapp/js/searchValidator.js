function validateAll()
{ 
	
		
		var customerName=document.searchAccount.customerName.value;
		var customerId=document.searchAccount.customerId.value;
		var accountNo=document.searchAccount.accountno.value;
		var panNo=document.searchAccount.panNo.value;
		
		if (!document.searchAccount.customerName.value.match(/^[a-zA-Z]+$/)){
	         document.getElementById('error').innerHTML="Please Enter only alphabets in text";
	         return false;
	    }	
		

		if (!document.searchAccount.customerId.value.match(/^[0-9]/)){
	         document.getElementById('error').innerHTML="Please enter valid customer id";
	         return false;
	    }	
		if (!document.searchAccount.accountNo.value.match(/^[0-9]/)){
	         document.getElementById('error').innerHTML="Please enter valid account number";
	         return false;
	    }	
		if (!document.searchAccount.panNo.value.match(/[A-Z]{5}\d{4}[A-Z]/)){
	         document.getElementById('error').innerHTML="Please enter valid pan number";
	         return false;
	    }	
		
	}