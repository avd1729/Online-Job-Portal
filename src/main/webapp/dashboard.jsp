<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Job Portal Dashboard</title>
</head>
<body>
<h2>Welcome to the Job Portal Dashboard</h2>
<%
    String username = (String) session.getAttribute("username");
    if (username != null) {
%>
<p>Hello, <%= username %></p>
<p>Please select a stream:</p>
<form action="JobListServlet" method="post">
    <input type="radio" name="stream" value="Science"> Science<br>
    <input type="radio" name="stream" value="Arts"> Arts<br><br>
    <input type="submit" value="Submit">
</form>
<% } else {
    // Session is invalid, redirect to login page
    response.sendRedirect("cat_login.jsp");
}
%>
<p><a href="logout.jsp">Logout</a></p>
</body>
</html>







