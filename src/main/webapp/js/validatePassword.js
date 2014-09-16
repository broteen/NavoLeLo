validPassword(){
		if(document.changePswd.currentPswd.value == "")
		{
		alert('Please input old password');
		document.changePswd.currentPswd.focus();
		return false;
		} 

		if(document.changePswd.newPswd.value == "")
		{
		alert('Please input Password');
		document.changePswd.newPswd.focus(); 
		return false;
		} 

		if(document.changePswd.confirmPswd.value == "")
		{
		alert('Please input Confirm Password');
		document.changePswd.confirmPswd.focus(); 
		return false;
		} 

		if(document.changePswd.newPswd.value != document.changePswd.confirmPswd.value)
		{
		alert('Confirm Password Not Match');
		document.changePswd.confirmPswd.focus(); 
		return false;
		} 
		document.changePswd.submit();

}