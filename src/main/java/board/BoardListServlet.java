package board;

import java.io.IOException;

import java.sql.*;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.post_info;

/**
 * Created by dongho on 1/1/17.
 */
@WebServlet("/board/list")
public class BoardListServlet extends HttpServlet
{
    @Override
    public void doGet(
        HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try
        {
           ServletContext sc = this.getServletContext();
           conn = (Connection)sc.getAttribute("conn");

           stmt = conn.createStatement();

           rs = stmt.executeQuery(
                   "select users.name, board.no, board.mod_date, board.title, board.contents" +
                   " from users inner join board" +
                   " on users.no = board.user_no" +
                   " order by board.mod_date desc");

           response.setContentType("text/html; charset=UTF-8");
           ArrayList<post_info> posts = new ArrayList<post_info>();

           while (rs.next())
           {
               posts.add(new post_info()
                   .setUserName(rs.getString("users.name"))
                   .setNo(rs.getInt("board.no"))
                   .setTitle(rs.getString("board.title"))
                   .setContents(rs.getString("board.contents"))
                   .setModifiedDate(rs.getDate("board.mod_date")));
           }

           request.setAttribute("posts", posts);

           RequestDispatcher rd = request.getRequestDispatcher("board_list.jsp");
           rd.include(request, response);
        }
        catch (Exception e)
        {
            request.setAttribute("error", e);
            RequestDispatcher rd = request.getRequestDispatcher("../error.jsp");

            rd.forward(request, response);
        }
        finally
        {
            try
            {
                if (rs != null)
                {
                    rs.close();
                }
            }
            catch (Exception e)
            {

            }
            try
            {
                if (stmt != null)
                {
                    stmt.close();
                }
            }
            catch (Exception e)
            {

            }
        }
    }
}
