package auth;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by dongho on 1/1/17.
 */

@WebServlet("/auth/signup")
public class UserSignupServlet extends HttpServlet
{
    @Override
    protected void doGet(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html; charset=UTF-8");

        RequestDispatcher rd = request.getRequestDispatcher(
                "signup.jsp");
        rd.forward(request, response);
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
            conn = (Connection)sc.getAttribute("conn");

            stmt = conn.prepareStatement(
                    "insert into users (email, pwd, name, cre_date, mod_date)" +
                            " values (?, ?, ?, now(), now())");

            stmt.setString(1, request.getParameter("email"));
            stmt.setString(2, request.getParameter("password"));
            stmt.setString(3, request.getParameter("name"));
            stmt.executeUpdate();

            response.setContentType("text/html; charset=UTF-8");

            RequestDispatcher rd = request.getRequestDispatcher("signup_result.jsp");

            request.setAttribute("result", new Integer(0));

            rd.forward(request, response);
        }
        catch (SQLException e)
        {
            RequestDispatcher rd = request.getRequestDispatcher("signup_result.jsp");
            request.setAttribute("result", new Integer(e.getErrorCode()));

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
}
