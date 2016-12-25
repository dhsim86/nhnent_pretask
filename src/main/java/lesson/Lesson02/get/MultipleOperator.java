package lesson.Lesson02.get;

/**
 * Created by dongho on 12/25/16.
 */
public class MultipleOperator implements Operator
{
    @Override
    public double execute(double a, double b) throws Exception
    {
        return a * b;
    }

    @Override
    public String getName()
    {
        return "*";
    }
}
