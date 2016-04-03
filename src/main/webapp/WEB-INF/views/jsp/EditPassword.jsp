<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="MainButtons.jsp" />
<form action="savePassword" method="POST">
 Old Password
 <input type="password" name="oldPassword" id="oldPassword" required autocomplete="off"><br>
 New Password
 <input type="password" name="newPassword" id="newPassword" required autocomplete="off"><br>
 Confirm New Password 
  <input type="password" name="confirmNewPassword" id="confirmNewPassword" required autocomplete="off"><br>
  <input type="submit" id="submit" value="save"><br>
</form>
	<a href="editProfile"><button>cancel</button></a>
</body>
<script type="text/javascript"
		src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
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
	</script>
</html>