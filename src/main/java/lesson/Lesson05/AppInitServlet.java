package lesson.Lesson05;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;

/**
 * Created by dongho on 12/31/16.
 */
@SuppressWarnings("seria")
public class AppInitServlet extends HttpServlet
{
    @Override
    public void init(ServletConfig config) throws ServletException
    {
        System.out.println("AppInitServlet 준비");
        super.init(config);

        try
        {
            ServletContext sc = this.getServletContext();
            Class.forName(sc.getInitParameter("driver"));
            Connection conn = DriverManager.getConnection(
                    sc.getInitParameter("url"),
                    sc.getInitParameter("username"),
                    sc.getInitParameter("password"));
            sc.setAttribute("conn", conn);

        }
        catch (Throwable e)
        {
            throw new ServletException(e);
        }
    }

    @Override
    public void destroy()
    {
        System.out.println("AppInitServlet 마무리..");
        super.destroy();

        Connection conn =
                (Connection)this.getServletContext().getAttribute("conn");

        try
        {
            if (conn != null && conn.isClosed() == false)
            {
                conn.close();
            }
        }
        catch (Exception e)
        {

        }
    }
}
