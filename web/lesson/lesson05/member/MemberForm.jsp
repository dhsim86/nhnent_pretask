<%--
  Created by IntelliJ IDEA.
  User: dongho
  Date: 12/31/16
  Time: 10:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register User</title>
</head>
<body>
    <h1>Register User</h1>

    <form action='/member/add' method='post'>
        Name: <input type='text' name='name'><br>
        Email: <input type='text' name='email'><br>
        Password: <input type='password', name='password'><br>
        <input type='submit' value='add'>
        <input type='reset' value='cancel'>
    </form>
</body>
</html>
