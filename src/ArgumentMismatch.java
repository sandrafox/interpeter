public class ArgumentMismatch extends ParserException {
    public ArgumentMismatch(String name, int line) {
        super("ARGUMENT NUMBER MISMATCH " + name + ":" + line);
    }
}
