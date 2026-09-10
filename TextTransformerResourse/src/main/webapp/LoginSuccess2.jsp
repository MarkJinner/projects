<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.gmail.logger.Logger" %>
	
<!DOCTYPE html>
<html>
<style>
body{
background-color:lightblue;
padding-top:200px;
color: white;
font-size:30px;
font-weight: normal;
text-align: center;
}
</style>
<head>
<meta charset="UTF-8">
<body>
		   <b>Login and Password!
	<p>You successfully logged into profile  
	<p>Сообщение из сервлета: <%= request.getAttribute("message") %></p>
</body>
</head>
</html>
