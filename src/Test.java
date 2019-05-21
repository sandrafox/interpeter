import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        for (int i = 1; i <= 20; i++) {
            System.out.println("Test №" + i);
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("tests\\" + i + ".in"), "UTF-8"));
                List<String> ask = reader.lines().collect(Collectors.toList());
                String answer = Interpreter.interpr(ask.subList(0, ask.size() - 1));
                if (answer.equals(ask.get(ask.size() - 1))) {
                    System.out.println("Accepted");
                } else {
                    System.out.println("Reject");
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("----------------------------------------------");
        }
    }
}
