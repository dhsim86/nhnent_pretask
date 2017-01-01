<%--
  Created by IntelliJ IDEA.
  User: dongho
  Date: 1/1/17
  Time: 4:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<jsp:useBean id="user"
             scope="session"
             class="common.user_info" />

<div style="background-color:#00008b;color:#ffffff;height:20px;padding: 5px;">

    <% if (user.getEmail() != null) { %>

    <span style="float:right;">
        <%=user.getName()%>
        <a style="color:white;"
           href="/auth/logout">Logout</a>
        <a style="color:white;"
           href="/auth/update?no=<%=user.getNo()%>"> Update</a>
    </span>

    <% } else { %>

    <span style="float:right;">
        <a style="color:white;"
           href="/auth/signup">Sign up</a>

    <% } %>
</div>
