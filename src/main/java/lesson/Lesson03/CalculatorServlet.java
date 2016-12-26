package lesson.Lesson03;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

/**
 * Created by dongho on 12/26/16.
 */
@WebServlet("/calc")
public class CalculatorServlet extends GenericServlet
{
    @Override
    public void service(ServletRequest request, ServletResponse response)
        throws ServletException, IOException
    {
        int a = Integer.parseInt(request.getParameter("a"));
        int b = Integer.parseInt(request.getParameter("b"));

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        PrintWriter writer = response.getWriter();

        writer.println("a=" + a + "," + "b=" + b + " results.");
        writer.println("a + b = " + (a + b));
        writer.println("a - b = " + (a - b));
        writer.println("a * b = " + (a * b));
        writer.println("a / b = " + ((float)a / (float)b));
        writer.println("a % b = " + (a % b));
    }
}
