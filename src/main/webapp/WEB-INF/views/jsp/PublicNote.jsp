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
<br>---------------------------------<br>
<h2>${currentPublicNote.title}</h2>
${currentPublicNote.dateTime}
<p>${currentPublicNote.content}</p>
<br><br>
Author: ${currentPublicNote.author.nickname}
<br>---------------------------------<br>
<form action="authorProfile" method="GET">
<input type="submit" value="see author profile">
</form>
</body>
</html>