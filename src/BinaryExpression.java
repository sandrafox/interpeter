import java.util.Map;

public class BinaryExpression implements Expression {
    private Expression left;
    private Expression right;
    private Token oper;
    private int result;
    private int line;
    private String string = null;

    public BinaryExpression(Expression e1, Token t, Expression e2, int i) {
        left = e1;
        oper = t;
        right = e2;
        line = i;
    }

    public int evaluate(Map<String, Integer> vars) throws ParserException {
        result = left.evaluate(vars);
        switch (oper) {
            case DIV:
                int r = right.evaluate(vars);
                if (r == 0) throw new RuntimeError(toString(), line);
                result /= r;
                break;
            case MOD:
                r = right.evaluate(vars);
                if (r == 0) throw new RuntimeError(toString(), line);
                result %= r;
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

    public String toString() {
        if (string == null) {
            string = "(" + left.toString() + oper.toString() + right.toString() + ")";
        }
        return string;
    }
}
