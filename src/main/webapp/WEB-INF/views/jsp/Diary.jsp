<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<br>---------------------------------<br>
<c:forEach items="${currentDiary.notes}" var="entry">
		<form action="note" method="GET">
		${entry.key}
		<input type="submit" value="read">
		<input type="hidden" value="${entry.key}" name="currentNoteTitle">
		<br>___
		</form>
</c:forEach>
</form>
</body>
</html>