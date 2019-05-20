import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FunctionCall implements Expression {
    private Map<String, Expression> body;
    private List<Expression> params;
    private Map<String, List<String>> args;
    private String name;

    public FunctionCall(Map<String, Expression> e1, Map<String, List<String>> a1, List<Expression> p1, String v) {
        body = e1;
        args = a1;
        params = p1;
        name = v;
    }

    @Override
    public int evaluate(Map<String, Integer> vars) {
        Map<String, Integer> nvars = new HashMap<>();
        int i = 0;
        for (String v : args.get(name)) {
            nvars.put(v, params.get(i).evaluate(vars));
            i++;
        }
        return body.get(name).evaluate(nvars);
    }
}
