import java.util.Map;

public class BinaryExpression implements Expression {
    private Expression left;
    private Expression right;
    private Token oper;
    private int result;

    public BinaryExpression(Expression e1, Token t, Expression e2) {
        left = e1;
        oper = t;
        right = e2;
    }

    public int evaluate(Map<String, Integer> vars) {
        result = left.evaluate(vars);
        switch (oper) {
            case DIV:
                result /= right.evaluate(vars);
                break;
            case MOD:
                result %= right.evaluate(vars);
                break;
            case MUL:
                result *= right.evaluate(vars);
                break;
            case PLUS:
                result += right.evaluate(vars);
                break;
            case MINUS:
                result -= right.evaluate(vars);
                break;
            case EQ:
                result = result == right.evaluate(vars) ? 1 : 0;
                break;
            case GT:
                result = result > right.evaluate(vars) ? 1 : 0;
                break;
            case LT:
                result = result < right.evaluate(vars) ? 1 : 0;
        }
        return result;
    }
}
