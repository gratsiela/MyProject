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
		<div>
			<label> email <span class="req">*</span>
			</label> <input type="email" name="email" required autocomplete="off" />
		</div>
		<div>
			<label> password <span class="req">*</span>
			</label> <input type="password" name="password" required autocomplete="off"
				id="password" />
		</div>
		<input type="submit" value="sign in">
	</form><br>
	------------------------<br>
	<a href="forgottenPassword">Forgotten password?</a>
	
</body>
</html>