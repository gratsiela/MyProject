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
    <body class="align">

  <div class="site__container">

    <div class="grid__container">

      <form action="signIn" method="post" class="form form--login">

 		<div class="form__field">
          <label for="login__username"><span class="hidden">Email</span></label>
          <input type="email" name="email" class="form__input" placeholder="Email" required autocomplete="off" >
        </div>

        <div class="form__field">
          <label for="login__password"><span class="hidden">Password</span></label>
          <input type="password" name="password" class="form__input" placeholder="Password" required autocomplete="off" >
        </div>

        <div class="form__field">
          <input type="submit" name="commit" value="Sign In">
        </div>

      </form>
    
      <center>
      <br>
      ${errorMessage}
      <br>
      <p class="text--center"> <a href="forgottenPassword">Forgotten password?</a></p>
      <br>
      <p class="text--center"> <a href="index">CANCEL</a></p>
      </center>
    </div>

  </div>

</body>
</html>