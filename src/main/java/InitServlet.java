import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;

/**
 * Created by dongho on 1/1/17.
 */
@SuppressWarnings("serial")
public class InitServlet extends HttpServlet
{
    @Override
    public void init(ServletConfig config) throws ServletException
    {
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