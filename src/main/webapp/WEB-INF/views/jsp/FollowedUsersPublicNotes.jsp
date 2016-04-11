<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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
 		   <h1 style="text-decoration:underline">All public notes of followed users</h1>
   		   </center>

<center>
<table>
		<col width="50">
  		<col width="100">
  		<col width="300">
 		<col width="50">
<c:forEach items="${followedUsersPublicNotes}" var="entry">
<tr>
		<form action="readFollowedUserPublicNote" method="GET">
		<td><center><h6>${entry.value.dateTime}<h6></center></td>
		<td><center>${entry.value.author.nickname}<center></td>
		<td>${entry.value.title}</td>
		<td><center><input type="submit" class="smallCancelButtonStyle" value="read"></center></td>
		<input type="hidden" value="${entry.key}" name="currentPublicNoteID">
		<input type="hidden" value="followedUsers" name="authors">
		</form>
</tr>
</c:forEach>
</table>
</center>

      <div class="footer_container_box">	  
		  	<div class="readmore">
		</div><!--close readmore-->
	  </div><!--close footer_container_box-->
	  
      <div class="footer_container_box">
	    <div class="readmore">
		</div><!--close readmore-->
	  </div><!--close footer_container_box-->
      <div class="footer_container_boxl">
		
	    <div class="readmore">
		</div><!--close readmore-->	  
	  </div><!--close footer_container_box1-->    
		  			  
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