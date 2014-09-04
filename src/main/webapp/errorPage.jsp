<!DOCTYPE html>
<html>
<head>
<meta charset="US-ASCII">
<title>Error Page</title>
</head>
<body>
	<h1>Oops, an error has occured</h1>
	<h2>Error Code:</h2>
	<span>${error.errorCode}</span>
	<strong>${error.customText}</strong>
	<h2>Requested URI:</h2>
	<span>${error.requestedUri}</span>
	<h2>Exception name:</h2>
	<span>${error.name}</span>
	<h2>Exception message:</h2>
	<span>${error.message}</span>
	<div></div>
</body>
</html>
