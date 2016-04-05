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
<c:forEach items="${allPublicNotes}" var="note">
		<form action="publicNote" method="GET">
		${note.title}
		<input type="submit" value="read">
		<input type="hidden" value="${note.id}" name="currentNoteID">
		<br>______________________________
		</form>
</c:forEach>
</body>
</html>