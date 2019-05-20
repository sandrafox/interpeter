import java.util.*;

public class Parser {
    private LexicalAnalyzer analyzer;
    private Map<String, List<String>> functionList;
    private Map<String, Expression> functionBodyList;
    private Set<String> variables;

    public Expression parse(List<String> s) {
        analyzer = new LexicalAnalyzer();
        if (s.size() > 1) {
            functionDefenitionList(s.subList(0, s.size() - 1));
        }
        analyzer.analyze(s.get(s.size() - 1));
        analyzer.nextToken();
        return program();
    }

    private void functionDefenitionList(List<String> s) {
        functionList = new HashMap<>();
        functionBodyList = new HashMap<>();
        variables = new HashSet<>();
        for (String func : s) {
            analyzer.analyze(func);
            analyzer.nextToken();
            functionDefenition();
        }
    }

    private void functionDefenition() {
        String name = analyzer.getCurString();
        analyzer.nextToken();
        analyzer.nextToken();
        List<String> args = argsList();
        functionList.put(name, args);
        analyzer.nextToken();
        analyzer.nextToken();
        analyzer.nextToken();
        variables.addAll(args);
        functionBodyList.put(name, expression());
        variables.removeAll(args);
        analyzer.nextToken();
    }

    private List<String> argsList() {
        List<String> args = new LinkedList<>();
        args.add(analyzer.getCurString());
        analyzer.nextToken();
        Token token = analyzer.getCurToken();
        while (token == Token.COMMA) {
            analyzer.nextToken();
            args.add(analyzer.getCurString());
            analyzer.nextToken();
            token = analyzer.getCurToken();
        }
        return args;
    }

    private Expression program() {
        return expression();
    }

    private Expression expression() {
        Token token = analyzer.getCurToken();
        switch (token) {
            case OPENP:
                return binaryExpression();
            case LBRACKET:
                return ifExpression();
            case ID:
                String name = analyzer.getCurString();
                if (variables.contains(name)) {
                    analyzer.nextToken();
                    return new Variable(name);
                }
                return functionCall();
            case MINUS:
            case NUMBER:
                return constExpression();
            default:
                return null;
        }
    }

    private FunctionCall functionCall() {
        String name = analyzer.getCurString();
        analyzer.nextToken();
        analyzer.nextToken();
        List<Expression> params = parametersList();
        analyzer.nextToken();
        return new FunctionCall(functionBodyList, functionList, params, name);
    }

    private List<Expression> parametersList() {
        List<Expression> params = new ArrayList<>();
        params.add(expression());
        Token token = analyzer.getCurToken();
        while (token == Token.COMMA) {
            analyzer.nextToken();
            params.add(expression());
            token = analyzer.getCurToken();
        }
        return params;
    }

    private IfExpression ifExpression() {
        analyzer.nextToken();
        Expression e1 = expression();
        analyzer.nextToken(); //]?(
        analyzer.nextToken();
        analyzer.nextToken();
        Expression e2 = expression();
        analyzer.nextToken();
        analyzer.nextToken();
        analyzer.nextToken();
        Expression e3 = expression();
        analyzer.nextToken();
        return new IfExpression(e1, e2, e3);
    }

    private BinaryExpression binaryExpression() {
        analyzer.nextToken();
        Expression e1 = expression();
        Token oper = analyzer.getCurToken();
        analyzer.nextToken();
        Expression e2 = expression();
        analyzer.nextToken();
        return new BinaryExpression(e1, oper, e2);
    }

    private Const constExpression() {
        Token token = analyzer.getCurToken();
        int value = 1;
        if (token == Token.MINUS) {
            value = -1;
            analyzer.nextToken();
        }
        value *= Integer.parseInt(analyzer.getCurString());
        analyzer.nextToken();
        return new Const(value);
    }
}
