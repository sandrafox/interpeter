import java.util.Map;

public class IfExpression implements Expression {
    private Expression cond;
    private Expression first;
    private Expression second;
    private int result;
    private String string = null;

    public IfExpression(Expression e1, Expression e2, Expression e3) {
        cond = e1;
        first = e2;
        second = e3;
    }

    @Override
    public int evaluate(Map<String, Integer> vars) throws ParserException {
        if (cond.evaluate(vars) != 0) {
            result = first.evaluate(vars);
        } else {
            result = second.evaluate(vars);
        }
        return result;
    }

    @Override
    public String toString() {
        if (string == null) {
            string = "[" + cond.toString() + "]?(" + first.toString() + "):(" + second.toString() + ")";
        }
        return string;
    }
}
