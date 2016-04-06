<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="signIn" method="POST">
			email
		<input type="email" name="email" required autocomplete="off" /><br>
			password 
		<input type="password" name="password" required autocomplete="off" /><br>
		<input type="submit" value="sign in">
	</form><br>
	------------------------<br>
	<a href="forgottenPassword">Forgotten password?</a>
	<br>
	------------------------<br>
	<a href="index"><button>cancel</button></a>
	
</body>
</html>