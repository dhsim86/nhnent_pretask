package lesson.Lesson04;

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by dongho on 12/28/16.
 */
public class MemberDeleteServlet extends HttpServlet
{
    @Override
    protected void doGet(
        HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        Connection conn = null;
        PreparedStatement stmt = null;

        try
        {
            ServletContext sc = this.getServletContext();
            conn = (Connection) sc.getAttribute("conn");

            stmt = conn.prepareStatement(
                    "delete from MEMBERS" +
                    " where MNO=?");
            stmt.setInt(1, Integer.parseInt(request.getParameter("no")));
            stmt.executeUpdate();

            response.sendRedirect("list");
        }
        catch (Exception e)
        {
            request.setAttribute("error", e);
            RequestDispatcher rd = request.getRequestDispatcher(
                    "/lesson/lesson05/member/Error.jsp");
            rd.forward(request, response);

            /*
            for (StackTraceElement element : e.getStackTrace())
            {
                System.out.println(element.toString());
            }
            throw new ServletException(e);*/
        }
        finally
        {
            try { if (stmt != null) stmt.close(); } catch(Exception e) {}
        }
    }
}
