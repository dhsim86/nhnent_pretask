<%--
  Created by IntelliJ IDEA.
  User: dongho
  Date: 12/31/16
  Time: 9:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login</title>
</head>
<body>
    <h2> User Login </h2>
    <form action="/auth/login" method="post">
    email: <input type="text" name="email"><br>
    password: <input type="password" name="password"><br>
    <input type="submit" value="Login">
    </form>
</body>
</html>
