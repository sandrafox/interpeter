public class BinaryExpression implements Expression {
    private Expression left;
    private Expression right;
    private Token oper;
    private int result;
    private boolean eval;

    public BinaryExpression(Expression e1, Token t, Expression e2) {
        left = e1;
        oper = t;
        right = e2;
        eval = false;
    }

    public BinaryExpression(int value) {
        result = value;
        eval = true;
    }

    public int evaluate() {
        if (eval) return result;
        result = left.evaluate();
        switch (oper) {
            case DIV:
                result /= right.evaluate();
                break;
            case MOD:
                result %= right.evaluate();
                break;
            case MUL:
                result *= right.evaluate();
                break;
            case PLUS:
                result += right.evaluate();
                break;
            case MINUS:
                result -= right.evaluate();
                break;
            case EQ:
                result = result == right.evaluate() ? 1 : 0;
                break;
            case GT:
                result = result > right.evaluate() ? 1 : 0;
                break;
            case LT:
                result = result < right.evaluate() ? 1 : 0;
        }
        return result;
    }
}
