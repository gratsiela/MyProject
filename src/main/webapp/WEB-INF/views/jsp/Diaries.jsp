<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
  <link rel="stylesheet" href="css/styleSignInUp.css">
</head>
<body>
		
 <div id="main">
  	<jsp:include page="MainButtons.jsp" />
    
	<div id="site_content" style="width:920px;">	 	 
	  <div id="content">
        <div class="content_item">
		  
		  <center>
 		   <h1 style="text-decoration:underline; padding-right: 60px;">Your diaries</h1>
   		   </center>
   		   
<c:forEach items="${signedUser.diaries}" var="entry">
		  <div class="content_container">
		  	<form action="diary" method="GET" class="form form--login">
		  	<div class="form__field">
            <input type="submit" value="${entry.value.name}">
       		</div>
			<input type="hidden" value="${entry.key}" name="currentDiaryID">
		</form>
		  </div><!--close content_container-->
</c:forEach>
		  			  
		</div><!--close content_item-->
      </div><!--close content-->
    
	</div><!--close site_content-->
  </div><!--close main-->
  
<div id="footer">
    <div id="footer_container">
	  <div class="footer_container_box">
	  </div><!--close footer_container_box-->
	  
      <div class="footer_container_box">
      		  <a href="createNewDiary"><button class="submitButtonStyle">NEW</button></a>
	  </div><!--close footer_container_box-->
	  
      <div class="footer_container_boxl">
	  </div><!--close footer_container_box1-->      
	 </div>
  </div><!--close footer-->  

</body>
</html>