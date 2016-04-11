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
 		   <h1 style="text-decoration:underline">Edit password</h1>
   		   </center>

<div class="site__container">

    <div class="grid__container">

      <form action="savePassword" method="POST" class="form form--login">

		  	<div class="form__field">
          <label for="login__password"><span class="hidden">Old password</span></label>
          <input id="oldPassword" type="password" name="oldPassword" class="form__input" placeholder="Old password" required autocomplete="off" >
        </div>

		<div class="form__field">
          <label for="login__password"><span class="hidden">New password</span></label>
          <input id="newPassword" type="password" name="newPassword" class="form__input" placeholder="New password" required autocomplete="off" >
        </div>	

		<div class="form__field">
          <label for="login__password"><span class="hidden">Confirm new password</span></label>
          <input id="confirmNewPassword" type="password" name="confirmNewPassword" class="form__input" placeholder="Confirm new password" required autocomplete="off" >
        </div>			

        <div class="form__field">
          <input id="submit" type="submit" value="Save">
        </div>

      </form>

      <center>
      <br>
      ${changePSWDmessage}
      <br>
      <p class="text--center"> <a href="profile"><button class="cancelButtonStyle">CANCEL</button></a></p>

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
	  <div class="footer_container_box">
	  </div> 
	  <br style="clear:both"/>
	  <br />
    </div>
  </div><!--close footer--> 

</body>

<!--  <script type="text/javascript"

<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
	<script type="text/javascript">
		$(function() {
			$("#submit").click(function() {
				var oldPassword = $("#oldPassword").val();
				var newPassword = $("#newPassword").val();
				var confirmNewPassword= $ ("#confirmNewPassword").val();
				if (oldPassword == newPassword){
					alert("The old and the new passwords are equal!");
					return false;
				}
				if (newPassword != confirmNewPassword) {
					alert("Confirmed password does not match with the new password!");
					return false;
				}
				return true;
			});
		});

	</script> -->

	</script>

</html>