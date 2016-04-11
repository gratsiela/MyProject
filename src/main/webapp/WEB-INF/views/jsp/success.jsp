<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>File Upload Success</title>
</head>
<body>
    <div class="success">
        File  <strong>${fileName}</strong> uploaded successfully.
        <br/><br/>
        <a href="<c:url value='/editProfile' />">Home</a> 
    </div>
</body>
</html>