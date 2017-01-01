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
@WebServlet("/board/view")
public class BoardViewServlet extends HttpServlet
{
    @Override
    public void doGet(
        HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        response.setContentType("text/html; charset=UTF-8");

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try
        {
            ServletContext sc = this.getServletContext();
            conn = (Connection)sc.getAttribute("conn");

            post_info post = PostView.getPost(conn, Integer.parseInt(request.getParameter("no")));

            request.setAttribute("post", post);

            RequestDispatcher rd = request.getRequestDispatcher("board_view.jsp");
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
