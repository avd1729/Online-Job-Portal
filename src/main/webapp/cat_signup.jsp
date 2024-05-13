<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Signup Page</title>
</head>
<body>
<h2>Signup</h2>
<form action="CATSignupServlet" method="post">
  Username: <input type="text" name="username"><br>
  Password: <input type="password" name="password"><br>
  Email: <input type="email" name="email"><br>
  <input type="submit" value="Signup">
</form>
</body>
</html>
