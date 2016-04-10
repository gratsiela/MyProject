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
<body class="align">

  <div class="site__container">

    <div class="grid__container">
    
<center>
<h1 style="text-decoration:underline;">Forgotten Password?</h1>
Enter your email address and your password will be send to it!
<br>
<br>
</center>

      <form action="sendForgottenPassword" method="post" class="form form--login">

 		<div class="form__field">
          <label for="login__username"><span class="hidden">Email</span></label>
          <input type="email" name="email" class="form__input" placeholder="Email" required autocomplete="off" >
        </div>

        <div class="form__field">
          <input type="submit" name="commit" value="SEND">
        </div>

      </form>
      <br>
      <p class="text--center"> <a href="signIn">CANCEL</a></p>
    </div>

  </div>
</body>
</html>