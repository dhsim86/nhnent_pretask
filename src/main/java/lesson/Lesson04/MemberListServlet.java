package lesson.Lesson04;

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

/**
 * Created by dongho on 12/27/16.
 */
@WebServlet("/member/list")
public class MemberListServlet extends GenericServlet
{
    private static final long serialVersionUID = 1L;

    @Override
    public void service(ServletRequest request, ServletResponse response)
        throws ServletException, IOException
    {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try
        {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());

            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost/studydb",
                    "study", "study");

            stmt = conn.createStatement();

            rs = stmt.executeQuery(
                    "select MNO, MNAME, EMAIL, CRE_DATE" +
                         " from MEMBERS" +
                         " order by MNO ASC");

            response.setContentType("text/html; charset=UTF-8");

            PrintWriter out = response.getWriter();

            out.println("<html><head><title>User list</title></head>");
            out.println("<body><h1>User list</h1>");

            out.println("<P><a href='add'>New user</a></p>");


            while (rs.next())
            {
                out.println(
                    "<a href='update?no=" +
                    rs.getInt("MNO") + " " +
                    "'>" +
                    rs.getString("MNAME") + "</a>," +
                    rs.getString("EMAIL") + ", " +
                    rs.getDate("CRE_DATE") + "<br>"
                );
            }

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
            try { if (rs != null) rs.close(); } catch (Exception e) { }
            try { if (stmt != null) stmt.close(); } catch (Exception e) { }
            try { if (conn != null) conn.close(); } catch (Exception e) { }
        }
    }
}
