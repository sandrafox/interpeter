import java.util.Map;

public class Variable implements Expression {
    private String name;

    public Variable(String v) {
        name = v;
    }

    @Override
    public int evaluate(Map<String, Integer> vars) {
        return vars.get(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
