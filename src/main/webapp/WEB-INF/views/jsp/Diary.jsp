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
<h2>${currentDiary.name}</h2>
<br>---------------------------------
<form action="deleteDiary" method="get">
<input type="submit" value="delete diary">
</form>
<br>
<form action="createNewNote" method="get">
<input type="submit" value="create new note">
</form>
</body>
</html>