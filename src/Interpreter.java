import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Interpreter {
    public static void main(String... args) {
        Scanner reader = new Scanner(System.in).useDelimiter("\0");
        List<String> expr = new ArrayList<>();
        while (reader.hasNextLine()) {
            expr.add(reader.nextLine());
        }
        System.out.println(interpr(expr));
    }

    public static String interpr(List<String> expr) {
        Parser parser = new Parser();
        try {
            return "" + parser.parse(expr).evaluate(null);
        } catch (ParserException e) {
            return e.getMessage();
        }
    }
}
