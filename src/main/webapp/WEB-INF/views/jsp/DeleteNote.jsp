<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
Are you sure you want to delete this note?<br>
<form action="deleteNote" method="POST">
<input type="submit" value="yes">
</form>
<form action="note" method="GET">
<input type="submit" value="no">
<input type="hidden" value="${currentNote.title}" name="currentNoteTitle">
</form>
</body>
</html>