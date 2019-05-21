public class ParametrNotFound extends ParserException {
    public ParametrNotFound(String name, int line) {
        super("PARAMETER NOT FOUND " + name + ":" + line);
    }
}
