<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="MainButtons.jsp" />
	<br> Hello, ${signedUser.firstName} ${signedUser.lastName} !
	<br>----------------------
	<br> nickname
	<br>
	<h2>${signedUser.nickname}</h2>
	<br>----------------------
	<br>photo
	<br>
	<h2>PHOTO HERE</h2>
	<br>----------------------
	<br>description
	<br>
	<h2>${signedUser.description}</h2>
	<br>----------------------
	<br>
		<a href="editProfile"><button>edit profile</button></a>

</body>
</html>