package auth;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.user_info;

/**
 * Created by dongho on 1/1/17.
 */
@WebServlet("/auth/login")
public class UserLoginServlet extends HttpServlet
{
    @Override
    protected void doGet(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        RequestDispatcher rd = request.getRequestDispatcher(
                "../index.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try
        {
            ServletContext sc = this.getServletContext();
            conn = (Connection) sc.getAttribute("conn");

            stmt = conn.prepareStatement(
                    "select no, name, email from users"
                            + " where email=? and pwd=?");
            stmt.setString(1, request.getParameter("email"));
            stmt.setString(2, request.getParameter("password"));
            rs = stmt.executeQuery();

            if (rs.next())
            {
                user_info user = new user_info().setNo(rs.getInt("no"))
                        .setEmail(rs.getString("email"))
                        .setName(rs.getString("name"));

                HttpSession session = request.getSession();
                session.setAttribute("user", user);

                response.sendRedirect("../board/list");
            }
            else
            {
                RequestDispatcher rd = request.getRequestDispatcher(
                        "login_fail.jsp");
                rd.forward(request, response);
            }
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
