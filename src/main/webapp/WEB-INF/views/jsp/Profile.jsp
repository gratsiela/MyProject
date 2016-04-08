<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7"
	crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css"
	integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r"
	crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
	integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
	crossorigin="anonymous"></script>
</head>
<body>

	<jsp:include page="MainButtons.jsp" />
	<br> Hello, ${signedUser.firstName} ${signedUser.lastName} !
	<br>----------------------
	<br> nickname:
	<h2>${signedUser.nickname}</h2>
	<br>----------------------
	<br>photo:
	<h2>PHOTO HERE</h2>
	<img alt="image"  src="<c:url value="/static/uploads/${signedUser.email}.png"/>">
	<br>----------------------
	<br>description:
	<h2>${signedUser.selfDescription}</h2>
	<br>----------------------
	<br>
		<a href="editProfile"><button>edit profile</button></a>

	<div class="container-fluid">
		<jsp:include page="MainButtons.jsp" />
		<div class="container">
			<div class='row'>
				<div class='col-md-12'>
					<h2>Hello, ${signedUser.firstName} ${signedUser.lastName}!</h2>
				</div>
				
				<div class='col-md-8 col-md-offset-2 text-center'>
					<h2> ${signedUser.nickname}</h2>

					<p>Photo:</p>
					<h2>PHOTO HERE</h2>
					<h7>${signedUser.photoURL}</h7>
					<img alt="image"  src="<c:url value="uploads/${signedUser.email}.png"/>">
					<img alt="image" src="${signedUser.photoURL}"> 
					<p>description:</p>
					<div class='col-md-8 col-md-offset-2'>
						<h4>${signedUser.selfDescription}</h4>
					</div>
					
				</div>
				<div class='col-md-2'></div>
			</div>
			<div class='row'>
			<div class='col-md-offset-10 col-md-2'>
			  <a href="editProfile" style="float:right;"><button class='button' >edit
							profile</button></a>
			</div>
			</div>
		</div>
		</div>

</body>
</html>