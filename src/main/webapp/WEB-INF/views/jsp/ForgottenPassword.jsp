<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
Forgotten Password?<br><br>
Enter your email address and your password will be send to it:<br>
<form action="sendForgottenPassword" method="POST">
email<input type="email" name="email">
<input type="submit" value="send password">
</form>
</body>
</html>