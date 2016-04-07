<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="//cdn.tinymce.com/4/tinymce.min.js"></script>
<script type="text/javascript">
tinymce.init({
    selector: "textarea",
    plugins: [
        "advlist autolink lists link image charmap print preview anchor",
        "searchreplace visualblocks code fullscreen",
        "insertdatetime media table contextmenu paste"
    ],
    toolbar: "insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image"
});
</script>
</head>
<body>
<jsp:include page="MainButtons.jsp" />
<br>---------------------------------
	<form action="createNewNote" method="POST">
	Title:<br>
	<input type="text" name="title"  required autocomplete="off" ><br>
	Content:<br>
	<textarea name="content"  ></textarea>
	
	Status:<br>
	<select name="status" required autocomplete="off" >
	<option value="private">private</option>
	<option value="public">public</option>
	</select><br>
	<input type="submit" value="create note">
	</form><br>
	<form action="diary" method="POST">
	<input type="submit" value="cancel";>
	</form>
</body>


</html>