<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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


		   <center>
 		   <h1 style="text-decoration:underline">Author profile</h1>
   		   </center>

<center>		  
		  		<br>
		  		<h5>Nickname</h5>
				<h1>${author.nickname}</h1>
				<c:choose>
				<c:when test = "${not empty author.photo}" >
					<img src="data:image/gif;base64,${author.getPhoto()}" style="width: 50%; height: 50%" alt=""/>
				</c:when>
				<c:otherwise>
				<img src="http://www.technoedit.com/wp-content/uploads/2015/02/no-user-profile-picture-whatsapp-268x300.jpg" width="200" height="150">
				</c:otherwise>
				</c:choose>
				<br><br>
				<br><br>
				<h5>Description</h5>
				<p><h1>${author.selfDescription}</h1><p>
</center>	 
		  			  
		</div><!--close content_item-->
      </div><!--close content-->
    
	</div><!--close site_content-->
  </div><!--close main-->
  
 <div id="footer">
    <div id="footer_container">
	  <div class="footer_container_box">
		<form action="${typeCurrentNote}" method="get">
		<input type="submit" class="submitButtonStyle" value="BACK">
		</form>
	  </div><!--close footer_container_box-->
	  
      <div class="footer_container_box">
	    	<form action="${followUnfollow}" method="POST">
			<input type="submit" class="submitButtonStyle" value="${followUnfollow}">
			</form>
	  </div><!--close footer_container_box-->
	  
      <div class="footer_container_boxl">
	  </div><!--close footer_container_box1-->      
	 </div>
  </div><!--close footer-->  
  
</body>

</html>