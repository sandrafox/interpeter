import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class interpreter {
    public static void main(String... args) {
        Scanner reader = new Scanner(System.in).useDelimiter("\0");
        List<String> expr = new ArrayList<>();
        while (reader.hasNextLine()) {
            expr.add(reader.nextLine());
        }
        interpr(expr);
    }

    public static void interpr(List<String> expr) {
        Parser parser = new Parser();
        System.out.println(parser.parse(expr).evaluate(null));
    }
}
