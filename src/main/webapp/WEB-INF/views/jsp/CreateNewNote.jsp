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
<br>---------------------------------
	<form action="createNewNote" method="POST">
	Title:<br>
	<input type="text" name="title">
	Content:<br>
	<input type="text" name="content">
	Status:
	<select name="status" required>
	<option value="private">private</opition>
	<option value="public">public</opition>
	</select><br>
	<input type="submit" value="create note">
	</form>
	<form action="diary" method="POST">
	<input type="submit" value="cancel";>
	</form>
</body>
</html>