<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
        <link rel="stylesheet" href="css/styleSignInUp.css">
</head>
<body>
<div id="main">
  	<jsp:include page="MainButtons.jsp" />
    
	<div id="site_content">	 	 
	  <div id="content">
        <div class="content_item">

		   <center>
 		   <h1 style="text-decoration:underline">Change picture</h1>
   		   </center>

<div class="site__container">

    <div class="grid__container">

      <form action="savePicture" method="POST" enctype="multipart/form-data" class="form form--login">

		<div class="form__field">
          <label for="login__password"><span class="hidden">Upload file</span></label>
          <input type="file" name="file" class="form__input" required autocomplete="off" >
        </div>			

        <div class="form__field">
          <input id="submit" type="submit" value="Save">
        </div>

      </form>

      <center>
      <br>
${errorMessage}
      <br>
      <p class="text--center"> <a href="editProfile"><button class="cancelButtonStyle">CANCEL</button></a></p>

      </div>

  </div>
  		  			  
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