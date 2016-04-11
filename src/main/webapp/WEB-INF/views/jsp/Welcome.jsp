<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
        <link rel="stylesheet" href="css/styleSignInUp.css">
<style>
body{
background-color: #3B3D40;
}
.txt{
color:#EEEEEE;
font-size:600%;
text-align:center;
margin-top:50px;
}
</style>
</head>
<body>
<div class="container">
  <div class="row">
    <div class="col-md-4">   
    </div>
    <div class="col-md-4"> 
    <h1 class="txt">TagLibro</h1>
    <br>
    <center>
    		<form action="signIn" method="GET" class="form form--login">
		  	<div class="form__field">
            <input type="submit" value="SIGN IN">
            </div>
			</form>
			<form action="signUp" method="GET" class="form form--login">
		  	<div class="form__field">
            <input type="submit" value="SIGN UP">
            </div>
			</form>
	</center>
    </div>
    <div class="col-md-4"> 
    </div>
  </div>
</div>
</body>
</html>