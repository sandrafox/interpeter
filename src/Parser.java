import java.util.*;

public class Parser {
    private LexicalAnalyzer analyzer;
    private Map<String, List<String>> functionList;
    private Map<String, Expression> functionBodyList;
    private Set<String> variables;
    private int line;
    private Token[] os = {Token.PLUS, Token.MINUS, Token.DIV, Token.MUL, Token.MOD, Token.LT, Token.GT, Token.EQ};
    private List<Token> opers = Arrays.asList(os);

    public Expression parse(List<String> s) throws ParserException {
        line = 1;
        analyzer = new LexicalAnalyzer();
        if (s.size() > 1) {
            functionDefenitionList(s.subList(0, s.size() - 1));
        }
        analyzer.analyze(s.get(s.size() - 1));
        analyzer.nextToken();
        return expression();
    }

    private void functionDefenitionList(List<String> s) throws ParserException {
        functionList = new HashMap<>();
        functionBodyList = new HashMap<>();
        variables = new HashSet<>();
        for (String func : s) {
            analyzer.analyze(func);
            analyzer.nextToken();
            functionDefenition();
            line++;
        }
    }

    private void functionDefenition() throws ParserException {
        if (analyzer.getCurToken() != Token.ID) {
            throw new SyntaxError();
        }
        String name = analyzer.getCurString();
        analyzer.nextToken();
        if (analyzer.getCurToken() != Token.OPENP) {
            throw new SyntaxError();
        }
        analyzer.nextToken();
        List<String> args = argsList();
        if (analyzer.getCurToken() != Token.CLOSEP) {
            throw new SyntaxError();
        }
        functionList.put(name, args);
        analyzer.nextToken();
        if (analyzer.getCurToken() != Token.EQ) {
            throw new SyntaxError();
        }
        analyzer.nextToken();
        if (analyzer.getCurToken() != Token.LCBRACKET) {
            throw new SyntaxError();
        }
        analyzer.nextToken();
        variables.addAll(args);
        functionBodyList.put(name, expression());
        variables.removeAll(args);
        if (analyzer.getCurToken() != Token.RCBRACKET) {
            throw new SyntaxError();
        }
        analyzer.nextToken();
        if (analyzer.getCurToken() != Token.EOL) {
            throw new SyntaxError();
        }
    }

    private List<String> argsList() throws ParserException {
        Token token = analyzer.getCurToken();
        if (token != Token.ID) {
            throw new SyntaxError();
        }
        List<String> args = new LinkedList<>();
        args.add(analyzer.getCurString());
        analyzer.nextToken();
        token = analyzer.getCurToken();
        while (token == Token.COMMA) {
            analyzer.nextToken();
            token = analyzer.getCurToken();
            if (token != Token.ID) {
                throw new SyntaxError();
            }
            args.add(analyzer.getCurString());
            analyzer.nextToken();
            token = analyzer.getCurToken();
        }
        return args;
    }

    private Expression expression() throws ParserException {
        Token token = analyzer.getCurToken();
        switch (token) {
            case OPENP:
                return binaryExpression();
            case LBRACKET:
                return ifExpression();
            case ID:
                String name = analyzer.getCurString();
                analyzer.nextToken();
                token = analyzer.getCurToken();
                if (token == Token.OPENP) {
                    return functionCall(name);
                }
                if (variables.contains(name)) {
                    return new Variable(name);
                }
                throw new ParametrNotFound(name, line);
            case MINUS:
            case NUMBER:
                return constExpression();
            default:
                return null;
        }
    }

    private FunctionCall functionCall(String name) throws ParserException {
        if (analyzer.getCurToken() != Token.OPENP) {
            throw new SyntaxError();
        }
        analyzer.nextToken();
        List<Expression> params = parametersList();
        if (analyzer.getCurToken() != Token.CLOSEP) {
            throw new SyntaxError();
        }
        analyzer.nextToken();
        return new FunctionCall(functionBodyList, functionList, params, name, line);
    }

    private List<Expression> parametersList() throws ParserException {
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

    private IfExpression ifExpression() throws ParserException {
        if (analyzer.getCurToken() != Token.LBRACKET) {
            throw new SyntaxError();
        }
        analyzer.nextToken();
        Expression e1 = expression();
        if (analyzer.getCurToken() != Token.RBRACKET) {
            throw new SyntaxError();
        }
        analyzer.nextToken(); //]?(
        if (analyzer.getCurToken() != Token.QUEST) {
            throw new SyntaxError();
        }
        analyzer.nextToken();
        if (analyzer.getCurToken() != Token.OPENP) {
            throw new SyntaxError();
        }
        analyzer.nextToken();
        Expression e2 = expression();
        if (analyzer.getCurToken() != Token.CLOSEP) {
            throw new SyntaxError();
        }
        analyzer.nextToken();
        if (analyzer.getCurToken() != Token.COLON) {
            throw new SyntaxError();
        }
        analyzer.nextToken();
        if (analyzer.getCurToken() != Token.OPENP) {
            throw new SyntaxError();
        }
        analyzer.nextToken();
        Expression e3 = expression();
        if (analyzer.getCurToken() != Token.CLOSEP) {
            throw new SyntaxError();
        }
        analyzer.nextToken();
        return new IfExpression(e1, e2, e3);
    }

    private BinaryExpression binaryExpression() throws ParserException {
        if (analyzer.getCurToken() != Token.OPENP) {
            throw new SyntaxError();
        }
        analyzer.nextToken();
        Expression e1 = expression();
        Token oper = analyzer.getCurToken();
        if (!opers.contains(oper)) {
            throw new SyntaxError();
        }
        analyzer.nextToken();
        Expression e2 = expression();
        if (analyzer.getCurToken() != Token.CLOSEP) {
            throw new SyntaxError();
        }
        analyzer.nextToken();
        return new BinaryExpression(e1, oper, e2, line);
    }

    private Const constExpression() throws ParserException {
        Token token = analyzer.getCurToken();
        int value = 1;
        if (token == Token.MINUS) {
            value = -1;
            analyzer.nextToken();
        }
        if (analyzer.getCurToken() != Token.NUMBER) {
            throw new SyntaxError();
        }
        value *= Integer.parseInt(analyzer.getCurString());
        analyzer.nextToken();
        return new Const(value);
    }
}
