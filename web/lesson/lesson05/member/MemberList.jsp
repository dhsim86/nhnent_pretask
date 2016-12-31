<%--
  Created by IntelliJ IDEA.
  User: dongho
  Date: 12/31/16
  Time: 7:43 PM
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
    <title>Member List</title>
</head>
<body>
   <jsp:include page="Header.jsp"/>

    <h1> Member List</h1>
    <p> <a href='add'> New Member</a> </p>

    <jsp:useBean id="members"
        scope="request"
        class="java.util.ArrayList"
        type="java.util.ArrayList<lesson.Lesson05.Member>"/>

    <%
        //ArrayList<Member> members = (ArrayList<Member>)request.getAttribute("members");

        for (Member member : members)
        {
    %>

    <%=member.getNo()%>,
    <a href='update?no=<%=member.getNo()%>'><%=member.getName()%></a>,
    <%=member.getEmail()%>,
    <%=member.getCreatedDate()%>
    <a href='delete?no=<%=member.getNo()%>'>[Delete]</a> <br>

    <%
        }
    %>
<!--    <jsp:include page="Tail.jsp"/> -->

</body>
</html>
