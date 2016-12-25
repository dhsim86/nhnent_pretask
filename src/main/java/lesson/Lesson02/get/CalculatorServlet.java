package lesson.Lesson02.get;

/**
 * Created by dongho on 12/25/16.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/lesson/lesson02/WebContent/CalculatorServlet")
public class CalculatorServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    private Hashtable<String, Operator> operatorTable =
            new Hashtable<String, Operator>();

    public CalculatorServlet()
    {
        operatorTable.put(new PlusOperator().getName(), new PlusOperator());
        operatorTable.put(new MinusOperator().getName(), new MinusOperator());
        operatorTable.put(new MultipleOperator().getName(), new MultipleOperator());
        operatorTable.put(new DivideOperator().getName(), new DivideOperator());
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
        throws ServletException, IOException
    {
        String op = request.getParameter("op");
        double v1 = Double.parseDouble(request.getParameter("v1"));
        double v2 = Double.parseDouble(request.getParameter("v2"));

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        out.println("<h1>Calculation Result</h1>");
        out.println("Result: ");

        try
        {
            Operator operator = operatorTable.get(op);

            if (operator == null)
            {
                out.println("Not supported operator.");
            }
            else
            {
                double result = operator.execute(v1, v2);
                out.println(String.format("%.3f", result));
            }
        }
        catch (Exception e)
        {
            out.println("Calculation Error.");
        }

        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        doGet(request, response);
    }
}
