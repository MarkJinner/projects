<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.gmail.logger.Logger" %>

<%
    Logger logger = new Logger();

    /* String errorUrl = (String)request.getAttribute("javax.servlet.forward.request_uri"); */
    String errorUrl = request.getServletPath(); 
    if (errorUrl == null) {
        /* errorUrl = (String) request.getAttribute("javax.servlet.error.request_uri"); */
        errorUrl = (String) request.getAttribute("javax.servlet.error.request_uri");
        
    	/* errorUrl = request.getRequestURL().toString(); */
    }
    logger.log("Error 404 for URL: " + errorUrl);
    
%>	
	
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
		   <b>RESOURSE UNDER CONSTRUCTION
	<p>REQUESTED PAGE NOT FOUND
</body>
</head>
</html>
