<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
    
	<div id="site_content">	 	 
	  <div id="content">
        <div class="content_item">

  		   <center>
 		   <h1 style="text-decoration:underline">Edit profile</h1>
   		   </center>

<div class="site__container">

    <div class="grid__container">

      <form action="saveProfile" method="POST" class="form form--login">

<h5>First name</h5>
		<div class="form__field">
          <label for="login__username"><span class="hidden">First name</span></label>
          <input type="text" name="firstName" value="${signedUser.firstName}" class="form__input" placeholder="First name" required autocomplete="off" >
        </div>
     
<h5>Last name</h5>   
        <div class="form__field">
          <label for="login__username"><span class="hidden">Last name</span></label>
          <input type="text" name="lastName" value="${signedUser.lastName}" class="form__input" placeholder="Last name" required autocomplete="off" >
        </div>
  
<h5>Nickname</h5>      
        <div class="form__field">
          <label for="login__username"><span class="hidden">Nickname</span></label>
          <input type="text" name="nickname" value="${signedUser.nickname}" class="form__input" placeholder="Nickname" required autocomplete="off" >
        </div>
    
<h5>Self description</h5>    
        <div class="form__field">
          <label for="login__username"><span class="hidden">Description</span></label>
          <textarea rows="5" name="description" class="form__input"  autocomplete="off" spellcheck="false" style='background-color: #434A52;border-bottom-left-radius: 0;
border-top-left-radius: 0;color:inherit;'>
          ${signedUser.selfDescription}
          </textarea>
        </div>

        <div class="form__field">
          <input type="submit" value="Save">
          
        </div>
			
      </form>
      <br>
          <p class="text--center"> <a href="changePicture"><button class="submitButtonStyle">Upload Picture</button></a>
      <br>
      
      <p class="text--center"> <a href="profile"><button class="cancelButtonStyle">CANCEL</button></a></p>
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