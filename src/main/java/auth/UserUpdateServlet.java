package auth;

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
import javax.servlet.http.HttpSession;

import common.user_info;

/**
 * Created by dongho on 1/1/17.
 */
@WebServlet("/auth/update")
public class UserUpdateServlet extends HttpServlet
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
                    "select no, email, name, cre_date from users" +
                            " where no=" + request.getParameter("no"));
            rs.next();

            response.setContentType("text/html; charset=UTF-8");
            user_info user = new user_info().setNo(rs.getInt("no"))
                    .setName(rs.getString("name"))
                    .setEmail(rs.getString("email"))
                    .setCreatedDate(rs.getDate("cre_date"));

            request.setAttribute("user", user);

            RequestDispatcher rd = request.getRequestDispatcher(
                    "update_form.jsp");
            rd.include(request, response);

        }
        catch (Exception e)
        {
            request.setAttribute("error", e);
            RequestDispatcher rd = request.getRequestDispatcher(
                    "../error.jsp");
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
            catch(Exception e)
            {

            }
            try
            {
                if (stmt != null)
                {
                    stmt.close();
                }
            }
            catch(Exception e)
            {

            }
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
                    "update users set email=?, name=?, mod_date=now()" +
                            " where no=?");
            stmt.setString(1, request.getParameter("email"));
            stmt.setString(2, request.getParameter("name"));
            stmt.setInt(3, Integer.parseInt(request.getParameter("no")));
            stmt.executeUpdate();

            HttpSession session = request.getSession();
            user_info user = (user_info)session.getAttribute("user");
            user.setName(request.getParameter("name"))
                .setEmail(request.getParameter("email"));

            response.sendRedirect("/board/list");
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
