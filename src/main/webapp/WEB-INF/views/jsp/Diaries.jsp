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
<br>
<a href="createNewDiary"><button>create new diary</button></a>
<br>
<br>
		<c:forEach items="${signedUser.diaries}" var="entry">
		<form action="diary" method="GET">
		<input type="submit" value="${entry.value.name}">
		<input type="hidden" value="${entry.key}" name="currentDiaryID">
		</form>
</c:forEach>
</body>
</html>