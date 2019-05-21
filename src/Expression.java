import java.util.Map;

public interface Expression {
    public int evaluate(Map<String, Integer> vars) throws ParserException;
}
