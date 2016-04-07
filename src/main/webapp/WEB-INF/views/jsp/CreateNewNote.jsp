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
	<input type="text" name="title"  required autocomplete="off" ><br>
	Content:<br>
	<input type="text" name="content"  required autocomplete="off" ><br>
	Status:<br>
	<select name="status" required autocomplete="off" >
	<option value="private">private</option>
	<option value="public">public</option>
	</select><br>
	<input type="submit" value="create note">
	</form><br>
	<form action="diary" method="POST">
	<input type="submit" value="cancel";>
	</form>
</body>
</html>