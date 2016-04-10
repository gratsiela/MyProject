<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
        <link rel="stylesheet" href="css/styleSignInUp.css">
</head>
<body>
<body>

    <body class="align">

  <div class="site__container">

    <div class="grid__container">

      <form action="signUp" method="post" class="form form--login">

        <div class="form__field">
          <label for="login__username"><span class="hidden">First name</span></label>
          <input type="text" name="firstName" class="form__input" placeholder="First name" required autocomplete="off" >
        </div>
        
        <div class="form__field">
          <label for="login__username"><span class="hidden">Last name</span></label>
          <input type="text" name="lastName" class="form__input" placeholder="Last name" required autocomplete="off" >
        </div>
        
        <div class="form__field">
          <label for="login__username"><span class="hidden">Nickname</span></label>
          <input type="text" name="nickname" class="form__input" placeholder="Nickname" required autocomplete="off" >
        </div>
        
        <div class="form__field">
          <label for="login__username"><span class="hidden">Email</span></label>
          <input type="email" name="email" class="form__input" placeholder="Email" required autocomplete="off" >
        </div>
        
        <div class="form__field">
          <label for="login__password"><span class="hidden">Password</span></label>
          <input id="password" type="password" name="password" class="form__input" placeholder="Password" required autocomplete="off" >
        </div>

		<div class="form__field">
          <label for="login__password"><span class="hidden">Confirm password</span></label>
          <input id="confrimPassword" type="password" name="confirmPassword" class="form__input" placeholder="Confirm password" required autocomplete="off" >
        </div>

        <div class="form__field">
          <input type="submit" value="Sign Up">
        </div>

      </form>
      <br>
      		<h7>${pswdMessage}</h7>
      <br>
      <p class="text--center"> <a href="index">CANCEL</a></p>
    </div>

  </div>

</body>
    
    
    
    
    
</body>
<script type="text/javascript"
		src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
	<script type="text/javascript">
		$(function() {
			$("#submit").click(function() {
				var password = $("#password").val();
				var confirmPassword = $("#confirmPassword").val();
				if (password != confirmPassword) {
					alert("Passwords do not match!");
					return false;
				}
				return true;
			});
		});
	</script>
</html>