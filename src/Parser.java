public class Parser {
    private LexicalAnalyzer analyzer;

    public Expression parse(String s) {
        analyzer = new LexicalAnalyzer(s);
        analyzer.nextToken();
        return program();
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
            default:
                return constExpression();
        }
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
