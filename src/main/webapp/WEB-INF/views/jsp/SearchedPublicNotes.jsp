<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>TagLibro</title>
<style>
table {
    width: 80%;
}

th, td {
    text-align: left;
    padding: 5px;
}

tr:nth-child(even){background-color: #f2f2f2}
</style>
</head>
<body>

<div id="main">
  	<jsp:include page="MainButtons.jsp" />
    
	<div id="site_content">	 	 
	  <div id="content">
        <div class="content_item">
        
         <center>
 		   <h1 style="text-decoration:underline">Results from search</h1>
 		   <h6>${searchedWords}</h6>
   		   </center>

<center>
<table>
		<col width="50">
  		<col width="100">
  		<col width="300">
 		<col width="50">
<c:forEach items="${searchedPublicNotes}" var="entry">
<tr>
		<td><center><h6>${entry.value.dateTime}</h6></center></td>
		
		<td><center><form action="authorProfile" method="POST"><input type="submit" class="smallCancelButtonStyle" value="${entry.value.author.nickname}">
		<input type="hidden" value="${entry.value.author.email}" name="currentPublicAuthorEmail">
		</form></center></td>
		
		<td>${entry.value.title}</td>
		<td><center><form action="readPublicNote" method="GET">
		<input type="submit" class="smallCancelButtonStyle" value="read">
		<input type="hidden" value="${entry.key}" name="currentPublicNoteID">
		</form></center></td>
</tr>
</c:forEach>
</table>
</center>  
		  			  
		</div><!--close content_item-->
      </div><!--close content-->
    
	</div><!--close site_content-->
  </div><!--close main-->
  
   <div id="footer">
    <div id="footer_container">
    </div>
  </div><!--close footer-->  
   
</body>
</html>