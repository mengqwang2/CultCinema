<%-- 
    Document   : registerSuccess
    Created on : Apr 25, 2013, 11:03:04 AM
    Author     : 52209388
--%>

<%@page import="Bean.Member"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Refresh" content="5;url=index.jsp">
        <title>Register Successfully!</title>
        <link href="./css/commonCss.css" rel="stylesheet">
    </head>
    <body>
         <%@include file="/header.jsp" %>
        <% Member m=(Member)request.getAttribute("member"); %>
        <h1>Your Member ID is: </h1>
        <% out.print(m.getMemberID()); %>
        
        <p>Send you to index page in 5 seconds!</p>
        <div id="footer">
         <%@include file="/footer.jsp" %>
        </div> 
    </body>
</html>
