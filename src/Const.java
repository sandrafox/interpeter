public class Const implements Expression {
    private int result;

    public Const(int value) {
        result = value;
    }

    public int evaluate() {
        return result;
    }
}
