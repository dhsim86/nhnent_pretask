package board;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.post_info;
import common.user_info;

/**
 * Created by dongho on 1/1/17.
 */

@WebServlet("/board/write")
public class BoardWriteServlet extends HttpServlet
{
    @Override
    protected void doGet(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html; charset=UTF-8");

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        if (null != request.getParameter("no"))
        {
            try
            {
                ServletContext sc = this.getServletContext();
                conn = (Connection)sc.getAttribute("conn");

                post_info post = PostView.getPost(conn, Integer.parseInt(request.getParameter("no")));

                request.setAttribute("post", post);
                RequestDispatcher rd = request.getRequestDispatcher(
                        "board_write.jsp");
                rd.forward(request, response);
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
                    if (null != stmt)
                    {
                        stmt.close();
                    }
                }
                catch (Exception e)
                {
                }
            }
        }
        else
        {
            RequestDispatcher rd = request.getRequestDispatcher(
                    "board_write.jsp");
            rd.forward(request, response);
        }
    }

    @Override
    protected void doPost(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        Connection conn = null;
        PreparedStatement stmt = null;

        try
        {
            ServletContext sc = this.getServletContext();
            conn = (Connection) sc.getAttribute("conn");

            HttpSession session = request.getSession();
            user_info user = (user_info)session.getAttribute("user");

            if (null == request.getParameter("no"))
            {
                stmt = conn.prepareStatement(
                        "insert into board (mod_date, title, contents, user_no)" +
                        " values (now(), ?, ?, ?)");
                stmt.setInt(3, user.getNo());
            }
            else
            {
                stmt = conn.prepareStatement(
                        "update board set" +
                        " mod_date = now(), title = ?, contents = ?" +
                        " where no = ?");
                stmt.setInt(3, Integer.parseInt(request.getParameter("no")));
            }

            stmt.setString(1, request.getParameter("title"));
            stmt.setString(2, request.getParameter("contents"));
            stmt.executeUpdate();

            response.sendRedirect("list");
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
                if (null != stmt)
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
