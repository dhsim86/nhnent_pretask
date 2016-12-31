<%--
  Created by IntelliJ IDEA.
  User: dongho
  Date: 12/31/16
  Time: 11:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>User Information</title>
</head>
<body>
    <jsp:include page="Header.jsp"/>

    <h1>User Information</h1>
    <form action='/member/update' method='post'>
        number: <input type="text" name="no" value="${member.no}" readonly><br>
        name: <input type="text", name="name", value="${member.name}"><br>
        email: <input type="text" name="email" value="${member.email}"><br>
        register date: ${member.createdDate}<br>
        <input type="submit" value="save">
        <input type="button" value="delete" onclick="location.href='delete?no=${member.no}'">
        <input type="button" value="cancel" onclick="location.href='list'">

    </form>

</body>
</html>
