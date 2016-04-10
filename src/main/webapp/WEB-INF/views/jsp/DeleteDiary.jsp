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

  		   <center>
 		   <h1 style="text-decoration:underline">Delete diary</h1>
   		   </center>

<center>
	<br><br>
	<h5>Are You sure You want to delete "${currentDiary.name}"?</h5>
	<br>
	<form action="deleteDiary" method="POST">
	<input type="submit" class="submitButtonStyle" value="DELETE"><br>
	</form>
	<br>
	<form action="diary" method="GET">
	<input type="submit" class="cancelButtonStyle" value="CANCEL"><br>
	<input type="hidden" value="${currentDiary.id}" name="currentDiaryID">
	</form>
	<br><br>
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