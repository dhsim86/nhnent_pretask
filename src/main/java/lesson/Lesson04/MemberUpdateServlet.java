package lesson.Lesson04;

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lesson.Lesson05.*;

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
            conn = (Connection) sc.getAttribute("conn");

            stmt = conn.createStatement();

            rs = stmt.executeQuery(
                    "select MNO, EMAIL, MNAME, CRE_DATE from MEMBERS" +
                    " where MNO=" + request.getParameter("no"));
            rs.next();

            response.setContentType("text/html; charset=UTF-8");
            Member member = new Member().setNo(rs.getInt("MNO"))
                            .setName(rs.getString("MNAME"))
                            .setEmail(rs.getString("EMAIL"))
                            .setCreatedDate(rs.getDate("CRE_DATE"));

            request.setAttribute("member", member);

            RequestDispatcher rd = request.getRequestDispatcher(
                    "/lesson/lesson05/member/MemberUpdateForm.jsp");
            rd.include(request, response);

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
            try { if (rs != null) rs.close(); } catch(Exception e) {}
            try { if (stmt != null) stmt.close(); } catch(Exception e) {}
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
        }
    }
}
