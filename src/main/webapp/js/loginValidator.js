function validate(){

	var username=document.login.username.value;
	var username=username.trim();
	var password=document.login.password.value;

	if(username == ''){
		document.getElementById('error').innerHTML="Please Enter Username";
		return false;
	}
	else if(password == ''){
		document.getElementById('error').innerHTML="Please Enter Password";
		return false;
	}
	if(username.length > 20){
		document.getElementById('error').innerHTML="Username is too long. Username should be less than 20 characters";
		return false;
	}
	if(password.length > 20){
		document.getElementById('error').innerHTML="Password is too long. Password should be less than 20 characters";
		return false;
	}

	if (!document.login.username.value.match(/^[a-zA-Z]+$/)){
		document.getElementById('error').innerHTML="Please Enter only alphabets in text";
		return false;
	}	
	else
		return true;
}