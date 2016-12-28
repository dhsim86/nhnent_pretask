package lesson.Lesson04;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by dongho on 12/27/16.
 */

@WebServlet("/member/add")
public class MemserAddServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(
        HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        response.setContentType("text/html; charset=UTF-8");

        PrintWriter out = response.getWriter();
        out.println("<html><head><title>Register user</title></head>");
        out.println("<body><h1>Register user</h1>");

        out.println("<form action='add' method='post'>");
        out.println("Name: <input type='text' name='name'><br>");
        out.println("Email: <input type='text' name='email'><br>");
        out.println("Password: <input type='password', name='password'><br>");
        out.println("<input type='submit' value='add'>");
        out.println("<input type='reset', value='cancel'>");
        out.println("</form>");
        out.println("</body></html>");
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
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost/studydb",
                    "study", "study");

            stmt = conn.prepareStatement(
                    "insert into MEMBERS (EMAIL, PWD, MNAME, CRE_DATE, MOD_DATE)" +
                    "values (?, ?, ?, NOW(), NOW())");

            stmt.setString(1, request.getParameter("email"));
            stmt.setString(2, request.getParameter("password"));
            stmt.setString(3, request.getParameter("name"));
            stmt.executeUpdate();

            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();

            out.println("<html><head><title>Register result</title></head>");
            out.println("<body>");
            out.println("<p>Registering succeed!");
            out.println("</body></html>");

            response.addHeader("Refresh", "1; url=list");
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
            try { if (stmt != null) stmt.close(); } catch(Exception e) { }
            try { if (conn != null) conn.close(); } catch(Exception e) { }
        }
    }
}
