package lesson.Lesson04;

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by dongho on 12/28/16.
 */
@SuppressWarnings("serial")

/*
@WebServlet(
    urlPatterns = {"/member/update"},
    initParams = {
        @WebInitParam(name="driver", value="com.mysql.jdbc.Driver"),
        @WebInitParam(name="url", value="jdbc:mysql://localhost/studydb"),
        @WebInitParam(name="username", value="study"),
        @WebInitParam(name="password", value="study")
    }
)
*/
public class MemberUpdateServlet extends HttpServlet
{
    @Override
    protected void doGet(
        HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try
        {
            ServletContext sc = this.getServletContext();
            Class.forName(sc.getInitParameter("driver"));

            conn = DriverManager.getConnection(
                    sc.getInitParameter("url"),
                    sc.getInitParameter("username"),
                    sc.getInitParameter("password"));
            stmt = conn.createStatement();

            rs = stmt.executeQuery(
                    "select MNO, EMAIL, MNAME, CRE_DATE from MEMBERS" +
                    " where MNO=" + request.getParameter("no"));
            rs.next();

            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();

            out.println("<html><head><title>User Information</title></head>");
            out.println("<body><h1>User Information</h1>");
            out.println("<form action='update' method='post'>");
            out.println("number: <input type='text' name='no' value='" +
                        request.getParameter("no") + "' readonly><br>");
            out.println("name: <input type='text' name='name' value='" +
                        rs.getString("MNAME") + "'><br>");
            out.println("email: <input type='text' name='email' value='" +
                        rs.getString("EMAIL") + "'><br>");
            out.println("register date: " + rs.getDate("CRE_DATE") + "<br>");
            out.println("<input type='submit' value='save'>");
            out.println("<input type='button' value='delete'" +
                        " onclick=\"location.href='delete?no=" + request.getParameter("no") + "';\">");
            out.println("<input type='button' value='cancel'" +
                        " onclick='location.href=\"list\"'>");
            out.println("</form>");
            out.println("</body></html>");
        }
        catch (Exception e)
        {
            for (StackTraceElement element : e.getStackTrace())
            {
                System.out.println(element.toString());
            }
            throw new ServletException(e);
        }
        finally
        {
            try { if (rs != null) rs.close(); } catch(Exception e) {}
            try { if (stmt != null) stmt.close(); } catch(Exception e) {}
            try { if (conn != null) conn.close(); } catch(Exception e) {}
        }
    }

    @Override
    protected void doPost(
        HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        request.setCharacterEncoding("UTF-8");

        Connection conn = null;
        PreparedStatement stmt = null;

        try
        {
            ServletContext sc = this.getServletContext();
            Class.forName(sc.getInitParameter("driver"));

            conn = DriverManager.getConnection(
                    sc.getInitParameter("url"),
                    sc.getInitParameter("username"),
                    sc.getInitParameter("password"));
            stmt = conn.prepareStatement(
                    "update MEMBERS set EMAIL=?, MNAME=?, MOD_DATE=now()" +
                    " where MNO=?");
            stmt.setString(1, request.getParameter("email"));
            stmt.setString(2, request.getParameter("name"));
            stmt.setInt(3, Integer.parseInt(request.getParameter("no")));
            stmt.executeUpdate();

            response.sendRedirect("list");
        }
        catch (Exception e)
        {
            for (StackTraceElement element : e.getStackTrace())
            {
                System.out.println(element.toString());
            }
            throw new ServletException(e);
        }
        finally
        {
            try { if (stmt != null) stmt.close(); } catch(Exception e) {}
            try { if (conn != null) conn.close(); } catch(Exception e) {}
        }
    }
}
