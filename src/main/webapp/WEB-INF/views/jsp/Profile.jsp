<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	 <div id="main">
  	<jsp:include page="MainButtons.jsp" />
    
	<div id="site_content">	 	 
	  <div id="content">
        <div class="content_item">

		  <div class="content_container">
		  	<h1>Hello, ${signedUser.firstName} ${signedUser.lastName} !</h1>
		  </div><!--close content_container-->

<center>		  
          <div class="content_container">
          <h1 style="text-decoration:underline">Your profile</h1>
          <br>
		  		<br><h5>Nickname</h5>
				<h1>${signedUser.nickname}</h1>
				
				<br><br>
				<h5>Description</h5>
				<p><h1>${signedUser.selfDescription}</h1><p>
		  </div><!--close content_container-->
</center>	 
		  			  
		</div><!--close content_item-->
      </div><!--close content-->
    
	</div><!--close site_content-->
  </div><!--close main-->
  
 <div id="footer">
    <div id="footer_container">
	  <div class="footer_container_box">
	  </div><!--close footer_container_box-->
	  
      <div class="footer_container_box">
	  </div><!--close footer_container_box-->
	  
      <div class="footer_container_boxl">
		  <a href="editProfile"><button class="submitButtonStyle">EDIT PROFILE</button></a>
		  <br>
		  <br>
		  <a href="editPassword"><button class="submitButtonStyle">EDIT PASSWORD</button></a>
	  </div><!--close footer_container_box1-->      
	 </div>
  </div><!--close footer-->  
	
</body>
</html>