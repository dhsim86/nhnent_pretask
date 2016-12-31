package lesson.Lesson05;

import java.io.IOException;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by dongho on 12/31/16.
 */
@WebServlet("/auth/logout")
public class LogoutServlet extends HttpServlet
{
    @Override
    protected void doGet(
        HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        session.invalidate();

        response.sendRedirect("login");
    }
}
