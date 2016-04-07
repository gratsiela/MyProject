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
	<form action="signUp" method="POST">
		first name 
		<input type="text" name="firstName" required autocomplete="off" /><br>
		last name 
		<input type="text" name="lastName" required autocomplete="off" /><br>
		nickname 
		<input type="text" name="nickname" required autocomplete="off" /><br>
		email  
		<input type="email" name="email" required autocomplete="off" /><br>
		password 
		<input type="password" name="password" required autocomplete="off"
				id="password" /><br>
		confirm password 
		<input type="password" name="confirmPassword" required
				autocomplete="off" id="confirmPassword" /><br>
		<input type="submit" id="submit" value="sign up">
	</form>
	<br>
	------------------------<br>
	<a href="index"><button>cancel</button></a>
</body>
<script type="text/javascript"
		src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
	<script type="text/javascript">
		$(function() {
			$("#submit").click(function() {
				var password = $("#password").val();
				var confirmPassword = $("#confirmPassword").val();
				if (password != confirmPassword) {
					alert("Passwords do not match!");
					return false;
				}
				return true;
			});
		});
	</script>
</html>