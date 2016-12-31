package lesson.Lesson04;

import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lesson.Lesson05.*;

/**
 * Created by dongho on 12/27/16.
 */
@WebServlet("/member/list")
public class MemberListServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

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

            /*
            Class.forName(sc.getInitParameter("driver"));

            conn = DriverManager.getConnection(
                    sc.getInitParameter("url"),
                    sc.getInitParameter("username"),
                    sc.getInitParameter("password")); */

            stmt = conn.createStatement();

            rs = stmt.executeQuery(
                    "select MNO, MNAME, EMAIL, CRE_DATE" +
                            " from MEMBERS" +
                            " order by MNO ASC");

            response.setContentType("text/html; charset=UTF-8");
            ArrayList<Member> members = new ArrayList<Member>();

            while (rs.next())
            {
                members.add(new Member()
                    .setNo(rs.getInt("MNO"))
                    .setName(rs.getString("MNAME"))
                    .setEmail(rs.getString("EMAIL"))
                    .setCreatedDate(rs.getDate("CRE_DATE")));
            }

            request.setAttribute("members", members);

            RequestDispatcher rd = request.getRequestDispatcher(
                    "/lesson/lesson05/member/MemberList.jsp");
            rd.include(request, response);

            /*
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
                        rs.getDate("CRE_DATE") + ", " +
                        "<a href='delete?no=" +
                            rs.getInt("MNO") + " " +
                        "'>" + "[Delete]" + "</a>" + "<br>"
                );
            }

            out.println("</body></html>");
            */
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
            throw new ServletException(e);
            */
        }
        finally
        {
            try { if (rs != null) rs.close(); } catch (Exception e) { }
            try { if (stmt != null) stmt.close(); } catch (Exception e) { }
        }
    }
}
