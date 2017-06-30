<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>upload</title>
    <meta name="keywords" content="keyword1,keyword2,keyword3">
    <meta name="description" content="this is my page">
    <meta name="content-type" content="text/html; charset=UTF-8">
  </head>
  
  <body>
  	<form action="upload" method="post"	enctype="multipart/form-data">
  		<input type="file" name="file" />
  		<input type="text" name="path" />
  		<input type="submit" />
  	</form>
  </body>
</html>
