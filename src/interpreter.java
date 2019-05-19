import java.util.Scanner;

public class interpreter {
    public static void main(String... args) {
        Scanner reader = new Scanner(System.in).useDelimiter("\0");
        String expr = reader.next();
        interpr(expr);
    }

    public static void interpr(String expr) {
        Parser parser = new Parser();
        System.out.println(parser.parse(expr).evaluate());
    }
}
