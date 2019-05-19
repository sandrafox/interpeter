public class IfExpression implements Expression {
    private Expression cond;
    private Expression first;
    private Expression second;
    private int result;
    private boolean eval;

    public IfExpression(Expression e1, Expression e2, Expression e3) {
        cond = e1;
        first = e2;
        second = e3;
        eval = false;
    }

    @Override
    public int evaluate() {
        if (eval) return result;
        if (cond.evaluate() != 0) {
            result = first.evaluate();
        } else {
            result = second.evaluate();
        }
        return result;
    }
}
