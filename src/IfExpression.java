import java.util.Map;

public class IfExpression implements Expression {
    private Expression cond;
    private Expression first;
    private Expression second;
    private int result;

    public IfExpression(Expression e1, Expression e2, Expression e3) {
        cond = e1;
        first = e2;
        second = e3;
    }

    @Override
    public int evaluate(Map<String, Integer> vars) {
        if (cond.evaluate(vars) != 0) {
            result = first.evaluate(vars);
        } else {
            result = second.evaluate(vars);
        }
        return result;
    }
}
