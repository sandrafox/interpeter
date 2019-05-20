import java.util.Map;

public class FunctionList {
    private Map<String, Expression> functionList;

    public void addFunction(String name, Expression body) {
        functionList.put(name, body);
    }

    public Expression getFunction(String name) {
        return functionList.get(name);
    }
}
