<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="http://tinymce.cachefly.net/4.0/tinymce.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>

</head>
<body>
	<textarea id="my_editor"></textarea>

	<iframe id="form_target" name="form_target" style="display: none"></iframe>
	<form id="my_form" action="/upload/" target="form_target" method="post"
		enctype="multipart/form-data"
		style="width: 0px; height: 0; overflow: hidden">
		<input name="image" type="file"
			onchange="$('#my_form').submit();this.value='';">
	</form>

	<script type="text/javascript">
		$(document).ready(function() {
			tinymce.init({
				selector : '#my_editor',
				plugins : [ "image" ],
				file_browser_callback : function(field_name, url, type, win) {
					if (type == 'image') {
						$('#my_form input').click();
					}
				}
			});
		});
	</script>

</body>

</html>