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
<br>---------------------------------<br>
<table border="1">
<c:forEach items="${followedUsersPublicNotes}" var="entry">
<tr>
		<form action="readFollowedUserPublicNote" method="GET">
		<td>${entry.value.dateTime}</td>
		<td>${entry.value.author.nickname}</td>
		<td>${entry.value.title}</td>
		<td><input type="submit" value="read"><td>
		<input type="hidden" value="${entry.key}" name="currentPublicNoteID">
		<input type="hidden" value="followedUsers" name="authors">
		</form>
</tr>
</c:forEach>
</table>
</body>
</html>