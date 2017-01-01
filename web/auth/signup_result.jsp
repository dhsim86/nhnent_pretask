<%--
  Created by IntelliJ IDEA.
  User: dongho
  Date: 1/1/17
  Time: 5:14 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="Refresh" content="1; url=../index.jsp">
    <title>Sign up result</title>
</head>
<body>

    <jsp:useBean id="result"
         scope="request"
         class="java.lang.Integer"
         type="java.lang.Integer"/>

    <%
        if (0 == result.intValue())
        {
    %>

    <h1>Sign up succeed!</h1>

    <%
        } else {
    %>

    <h1>Sign up failed!</h1>
    <%
        }
    %>

</body>
</html>
