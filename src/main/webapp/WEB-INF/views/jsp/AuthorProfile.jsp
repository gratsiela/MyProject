<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
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
	<jsp:include page="MainButtons.jsp" />
	<br>----------------------
	<br> nickname:
	<h2>${author.nickname}</h2>
	<br>----------------------
	<br>photo:
	<h2>PHOTO HERE</h2>
	<br>----------------------
	<br>description:
	<h2>${author.selfDescription}</h2>
	<br>----------------------
	
	<br>
	<form action="${followUnfollow}" method="POST">
	<input type="submit" value="${followUnfollow}">
	</form>
	<br>---------------------------------<br>
<form action="${typeCurrentNote}" method="get">
<input type="submit" value="back">
</form>
</body>

</html>