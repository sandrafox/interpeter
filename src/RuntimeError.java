public class RuntimeError extends ParserException {
    public RuntimeError(String expression, int line) {
        super("RUNTIME ERROR " + expression + ":" + line);
    }
}
