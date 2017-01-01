<%--
  Created by IntelliJ IDEA.
  User: dongho
  Date: 1/1/17
  Time: 4:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign up</title>
</head>
<body>

<h1>Sign up</h1>

<form action='signup' method='post'>
    Name: <input type='text' name='name'><br>
    Email: <input type='text' name='email'><br>
    Password: <input type='password', name='password'><br>
    <input type='submit' value='add'>
    <input type='reset' value='cancel'>
</form>

</body>
</html>
