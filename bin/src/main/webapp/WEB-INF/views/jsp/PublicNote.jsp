<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div id="main">
  	<jsp:include page="MainButtons.jsp" />
    
	<div id="site_content">	 	 
	  <div id="content">
        <div class="content_item">
		  <div class="content_container">
		  </div><!--close content_container-->
		  
          <div class="content_container">
		  		<center>
		  		<h1>${currentPublicNote.title}</h1>
		  		<br>
				<h6>${currentPublicNote.dateTime}</h6>
				<br>
				<p>${currentPublicNote.content}</p>
				<br>
				<h6>${currentPublicNote.author.nickname}</h6>
				</center>
		  </div><!--close content_container-->  			  
		</div><!--close content_item-->
      </div><!--close content-->
    
	</div><!--close site_content-->
  </div><!--close main-->
  
  <div id="footer">
    <div id="footer_container">
	  <div class="footer_container_box">
		<form action="${subpage}" method="get">
		<input type="submit" class="submitButtonStyle" value="BACK">
		</form>
	  </div><!--close footer_container_box-->
	  
      <div class="footer_container_box">
	    <form action="authorProfile" method="GET">
		<input type="submit" class="submitButtonStyle" value="AUTHOR">
		</form>
	  </div><!--close footer_container_box-->
	  
      <div class="footer_container_boxl">
	  </div><!--close footer_container_box1-->      
	 </div>
  </div><!--close footer-->  
	
</body>
</html>