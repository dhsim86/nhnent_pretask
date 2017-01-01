<%--
  Created by IntelliJ IDEA.
  User: dongho
  Date: 1/1/17
  Time: 8:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>게시판</title>
</head>
<body>

    <jsp:useBean id="user"
         scope="session"
         class="common.user_info" />

    <jsp:useBean id="post"
         scope="request"
         class="common.post_info" />

<table>
    <form action="write" method="post">
        <tr>
            <td>
                <table width="100%" cellpadding="0" cellspacing="0" border="0">
                    <tr style="background:url('/img/table_mid.gif') repeat-x; text-align:center;">
                        <td width="5"><img src="/img/table_left.gif" width="5" height="30" /></td>
                        <td>글쓰기</td>
                        <td width="5"><img src="/img/table_right.gif" width="5" height="30" /></td>
                    </tr>
                </table>
                <table>
                    <%
                        if (0 != post.getNo())
                        {
                    %>
                    <tr>
                        <td>&nbsp;</td>
                        <td align="center">글번호</td>
                        <td><input name="no" size="50" maxlength="100" value="<%=post.getNo()%>"></td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr height="1" bgcolor="#dddddd"><td colspan="4"></td></tr>
                    <%  } else {
                            post.setTitle("");
                            post.setContents("");
                        }
                    %>

                    <tr>
                        <td>&nbsp;</td>
                        <td align="center">제목</td>
                        <td><input name="title" size="50" maxlength="100" value="<%=post.getTitle()%>"></td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr height="1" bgcolor="#dddddd"><td colspan="4"></td></tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td align="center">이름</td>
                        <td><input type="text" name="name" size="50" maxlength="50" value="<%=user.getName()%>" readonly></td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr height="1" bgcolor="#dddddd"><td colspan="4"></td></tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td align="center">내용</td>
                        <td><textarea name="contents" cols="50" rows="13" > <%=post.getContents()%> </textarea></td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr height="1" bgcolor="#dddddd"><td colspan="4"></td></tr>
                    <tr height="1" bgcolor="#82B5DF"><td colspan="4"></td></tr>
                    <tr align="center">
                        <td>&nbsp;</td>
                        <td colspan="2">
                            <input type=submit value="등록">
                            <input type=button value="취소" onclick="location.href='list'">
                        <td>&nbsp;</td>
                    </tr>
                </table>
            </td>
        </tr>
    </form>
</table>
</body>
</html>
