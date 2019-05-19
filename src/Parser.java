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
            case CLOSEP:
                analyzer.nextToken();
                Expression e1 = expression();
                Token oper = analyzer.getCurToken();
                analyzer.nextToken();
                Expression e2 = expression();
                analyzer.nextToken();
                return new BinaryExpression(e1, oper, e2);
            default:
                return constExpression();
        }
    }

    private Const constExpression() {
        Token token = analyzer.getCurToken();
        int value = 1;
        if (token == Token.MINUS) {
            value = -1;
            analyzer.nextToken();
        }
        value *= Integer.parseInt(analyzer.getCurString());
        return new Const(value);
    }
}
