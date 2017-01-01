<%--
  Created by IntelliJ IDEA.
  User: dongho
  Date: 1/1/17
  Time: 6:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="common.post_info" %>
<%@ page import="java.util.ArrayList" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <title>Board</title>
</head>
<body>

    <jsp:include page="../header.jsp"/>

    <table width="100%" cellpadding="0" cellspacing="0" border="0">
        <tr height="5"><td width="5"></td></tr>
        <tr style="background:url('/img/table_mid.gif') repeat-x; text-align:center;">
            <td width="5"><img src="/img/table_left.gif" width="5" height="30" /></td>
            <td width="73">번호</td>
            <td width="379">제목</td>
            <td width="73">작성자</td>
            <td width="164">작성일</td>
            <td width="7"><img src="/img/table_right.gif" width="5" height="30" /></td>
        </tr>

        <jsp:useBean id="posts"
            scope="request"
            class="java.util.ArrayList"
            type="java.util.ArrayList<common.post_info>"/>

        <%
            if (posts.isEmpty()) {
        %>
                <tr align="center" bgcolor="#FFFFFF" height="30">
                    <td colspan="6">등록된 글이 없습니다.</td>
                </tr>
        <%
            } else {
                for (post_info post : posts)
                {
        %>
                    <tr height="25" align="center">
                        <td>&nbsp;</td>
                        <td><%=post.getNo() %></td>
                        <td align="left"><a href="view?no=<%=post.getNo()%>"> <%=post.getTitle() %> </a> </td>
                        <td align="center"><%=post.getUserName() %></td>
                        <td align="center"><%=post.getModifiedDate() %></td>
                        <td>&nbsp;</td>
                    </tr>
        <%
                }
            }
        %>

        <tr height="25" align="center">
        </tr>
        <tr height="1" bgcolor="#D2D2D2"><td colspan="6"></td></tr>

        <tr height="1" bgcolor="#82B5DF"><td colspan="6" width="752"></td></tr>
    </table>

    <table width="100%" cellpadding="0" cellspacing="0" border="0">
        <tr><td colspan="4" height="5"></td></tr>
        <tr align="center">
            <td><input type=button value="글쓰기" onclick="location.href='write'"></td>
    </tr>

</body>
</html>
