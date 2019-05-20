import java.util.Map;

public class Const implements Expression {
    private int result;

    public Const(int value) {
        result = value;
    }

    public int evaluate(Map<String, Integer> vars) {
        return result;
    }
}
