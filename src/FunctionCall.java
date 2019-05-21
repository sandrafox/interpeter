import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FunctionCall implements Expression {
    private Map<String, Expression> body;
    private List<Expression> params;
    private Map<String, List<String>> args;
    private String name;
    private int line;
    private String string = null;

    public FunctionCall(Map<String, Expression> e1, Map<String, List<String>> a1, List<Expression> p1, String v, int i) {
        body = e1;
        args = a1;
        params = p1;
        name = v;
        line = i;
    }

    @Override
    public int evaluate(Map<String, Integer> vars) throws ParserException {
        Map<String, Integer> nvars = new HashMap<>();
        int i = 0;
        List<String> arg = args.get(name);
        if (arg == null) {
            throw new FunctionNotFound(name, line);
        }
        if (arg.size() != params.size()) {
            throw new ArgumentMismatch(name, line);
        }
        for (String v : arg) {
            nvars.put(v, params.get(i).evaluate(vars));
            i++;
        }
        return body.get(name).evaluate(nvars);
    }

    @Override
    public String toString() {
        if (string == null) {
            string = name + "(" + String.join(",", params.stream().map(p -> p.toString()).collect(Collectors.toList())) +
                    ")";
        }
        return string;
    }
}
