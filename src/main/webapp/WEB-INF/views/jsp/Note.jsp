<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 <div id="main">
  	<jsp:include page="MainButtons.jsp" />
    
	<div id="site_content">	 	 
	  <div id="content">
	  
	  		  		  	<center>
	  
        <div class="content_item">
		  		<h1>${currentNote.title}</h1>
		  		<br>
				<h6>${currentNote.dateTime}</h6>
				<br>
				<p>${currentNote.content}</p>
				<br>
				<h6>${currentNote.status}</h6>
		</div><!--close content_item-->
				  		</center>
		
      </div><!--close content-->
	</div><!--close site_content-->
  </div><!--close main-->
  
  <div id="footer">
    <div id="footer_container">
	  <div class="footer_container_box">
		<form action="diary" method="POST">
		<input type="submit" class="submitButtonStyle" value="BACK">
		</form>
	  </div><!--close footer_container_box-->
	  
      <div class="footer_container_box">
      <form action="editNote" method="GET">
		<input type="submit" class="submitButtonStyle" value="EDIT NOTE">
		</form>
	  </div><!--close footer_container_box-->
	  
      <div class="footer_container_boxl">
		  <a href="deleteNote"><button class="submitButtonStyle">DELETE</button></a>
	  </div><!--close footer_container_box1-->      
	 </div>
  </div><!--close footer-->  
	
</body>
</html>