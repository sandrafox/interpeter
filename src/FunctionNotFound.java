public class FunctionNotFound extends ParserException {
    public FunctionNotFound(String name, int line) {
        super("FUNCTION NOT FOUND " + name + ":" + line);
    }
}
