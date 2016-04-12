<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
 
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Spring 4 MVC File Upload Example</title>
    <link href="<c:url value='/static/css/bootstrap.css' />"  rel="stylesheet" type="text/css"></link>
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet" type="text/css"></link>
</head>
<form action="savePicture" method="POST" enctype="multipart/form-data">
<input type="file" name="file" required autocomplete="off"/><br>
<input type="submit" value="save"><br>
</form>
<a href="editProfile"><button>cancel</button></a>
<br>
${errorMessage}
</html>