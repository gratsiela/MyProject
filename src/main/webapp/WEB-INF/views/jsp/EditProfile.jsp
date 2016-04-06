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
	<br>
	<form action="saveProfile" method="POST">
		First Name <input type="text" value="${signedUser.firstName}" name="firstName" required autocomplete="off"><br>
		Last Name<input type="text" value="${signedUser.lastName}" name="lastName" required autocomplete="off"><br>
		Nickname<input type="text" value="${signedUser.nickname}" name="nickname" required autocomplete="off"><br>
		Description <input type="text" value="${signedUser.selfDescription}" name="description"><br>
		<input type="submit" value="save"><br>
	</form>
	<a href="profile"><button>cancel</button></a>
	<br>-------------------------------
	<br>
	<a href="editPassword"><button>edit password</button></a>
	<br>
	<form action="singleUpload" method="GET">
		<input type="submit" name="button" value="Change photo" >
	</form>
</body>
</html>