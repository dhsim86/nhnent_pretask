<%--
  Created by IntelliJ IDEA.
  User: dongho
  Date: 12/31/16
  Time: 11:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="lesson.Lesson05.Member" %>
<%@ page import="java.util.ArrayList" %>

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

    <jsp:useBean id="member"
                scope="request"
                class="lesson.Lesson05.Member"
                type="lesson.Lesson05.Member"/>

    <h1>User Information</h1>
    <form action='/member/update' method='post'>
        number: <input type="text" name="no" value=<%=request.getParameter("no")%> readonly><br>
        name: <input type="text", name="name", value=<%=member.getName()%>><br>
        email: <input type="text" name="email" value=<%=member.getEmail()%>><br>
        register date: <%=member.getCreatedDate()%><br>
        <input type="submit" value="save">
        <input type="button" value="delete" onclick="location.href='delete?no=<%=request.getParameter("no")%>'">
        <input type="button" value="cancel" onclick="location.href='list'">

    </form>

</body>
</html>
