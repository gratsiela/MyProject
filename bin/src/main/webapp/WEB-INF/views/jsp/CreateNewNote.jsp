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
	
<div id="main">
  	<jsp:include page="MainButtons.jsp" />
    
	<div id="site_content">	 	 
	  <div id="content">
        <div class="content_item">

		   <center>
 		   <h1 style="text-decoration:underline">New note</h1>
   		   </center>

<center>
<form action="createNewNote" method="POST">
	<br><br>
	<h5>Title</h5>
	<input type="text" name="title" style="width:400px; height:30px; font-size:20px; text-align:center;"  required autocomplete="off" >
	<br><br>
	<textarea rows="20" name="content"  ></textarea>
	<br>
	<h5>Status</h5>
	<select name="status" style="font-size:20px" required autocomplete="off" >
	<option value="private">private</option>
	<option value="public">public</option>
	</select><br><br><br><br>
	<input type="submit" class="submitButtonStyle" value="SAVE">
	</form><br>
	<form action="diary" method="POST">
	<input type="submit" class="cancelButtonStyle" value="CANCEL">
	</form>
	
</center>
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