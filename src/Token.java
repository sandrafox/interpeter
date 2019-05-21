public enum Token {
    NUMBER, ID,
    PLUS, MINUS, DIV, MUL, MOD, LT, GT, EQ,
    LBRACKET, RBRACKET, OPENP, CLOSEP, LCBRACKET, RCBRACKET,
    QUEST, COLON, COMMA,
    EOL;

    @Override
    public String toString() {
        switch (this) {
            case LT:
                return "<";
            case GT:
                return ">";
            case EQ:
                return "=";
            case MINUS:
                return "-";
            case PLUS:
                return "+";
            case MUL:
                return "*";
            case MOD:
                return "%";
            case DIV:
                return "/";
            case COMMA:
                return ",";
            case ID:
                return "ID";
            case OPENP:
                return "(";
            case NUMBER:
                return "NUMBER";
            case LBRACKET:
                return "[";
            case EOL:
                return "\n";
            case COLON:
                return ":";
            case QUEST:
                return "?";
            case CLOSEP:
                return ")";
            case RBRACKET:
                return "]";
            case LCBRACKET:
                return "{";
            case RCBRACKET:
                return "}";
        }
        return "";
    }
}
