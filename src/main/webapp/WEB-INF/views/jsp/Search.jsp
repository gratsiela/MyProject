<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>TagLibro</title>
</head>
<body>
<div id="main">
  	<jsp:include page="MainButtons.jsp" />
    
	<div id="site_content">	 	 
	  <div id="content">
        <div class="content_item">

  		   <center>
 		   <h1 style="text-decoration:underline">Search</h1>
   		   </center>

<center>
	<form action="searchedPublicNotes" method="GET">
	<br><br><br>
	<input type="text" name="searchedWords" style="width:300px; height:30px; font-size:20px; text-align:center" required autocomplete="off">
	<br><br>
	<input type="submit" class="submitButtonStyle" value="SEARCH"><br>
	</form>
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