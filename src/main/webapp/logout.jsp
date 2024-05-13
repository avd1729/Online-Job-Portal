<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Logout</title>
</head>
<body>
<h2>Logout</h2>
<%
    // Invalidate the session to logout the user
    session.invalidate();
%>
<p>You have been logged out successfully.</p>
<p><a href="cat_login.jsp">Login again</a></p>
</body>
</html>

