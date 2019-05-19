public class LexicalAnalyzer {
    private char curChar = 0;
    private String curString;
    private Token curToken;
    private String s;
    private int curPos;

    public LexicalAnalyzer (String is) {
        s = is;
        curPos = -1;
        nextChar();
    }

    private void nextChar() {
        curPos++;
        curChar = s.charAt(curPos);
    }

    public void nextToken() {
        curString = "" + curChar;
        switch (curChar) {
            case '(':
                nextChar();
                curToken = Token.OPENP;
                break;
            case ')':
                nextChar();
                curToken = Token.CLOSEP;
                break;
            case '+':
                nextChar();
                curToken = Token.PLUS;
                break;
            case '-':
                nextChar();
                curToken = Token.MINUS;
                break;
            case '*':
                nextChar();
                curToken = Token.MUL;
                break;
            case '/':
                nextChar();
                curToken = Token.DIV;
                break;
            case '%':
                nextChar();
                curToken = Token.MOD;
                break;
            case '>':
                nextChar();
                curToken = Token.GT;
                break;
            case '<':
                nextChar();
                curToken = Token.LT;
                break;
            case '=':
                nextChar();
                curToken = Token.EQ;
                break;
            case '[':
                nextChar();
                curToken = Token.LBRACKET;
                break;
            case ']':
                nextChar();
                curToken = Token.RBRACKET;
                break;
            case '?':
                nextChar();
                curToken = Token.QUEST;
                break;
            case ':':
                nextChar();
                curToken = Token.COLON;
                break;
            default:
                if (Character.isDigit(curChar)) {
                    nextChar();
                    StringBuilder sb = new StringBuilder(curString);
                    while (Character.isDigit(curChar)) {
                        sb.append(curChar);
                        nextChar();
                    }
                    curString = sb.toString();
                    curToken = Token.NUMBER;
                }

        }
    }

    public Token getCurToken() {
        return curToken;
    }

    public int getCurPos() {
        return curPos;
    }

    public String getCurString() {
        return curString;
    }
}
