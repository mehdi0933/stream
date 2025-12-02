import org.example.calculeInterface.Calcule;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Object> numbers = new ArrayList<>();

        System.out.println("calucle tapez '=' pour terminer :");

        while (true) {
            String input = scanner.next();

            if (input.equals("=")) {
                break;
            }

            if (input.matches("\\d+")) {
                numbers.add(Integer.parseInt(input));
            } else if (input.matches("[+\\-*/]")) {
                numbers.add(input);
            } else {
                System.out.println("Entrée invalide : " + input);
            }
        }
        for (int i = 0; i < numbers.size(); i++) {
            Object token = numbers.get(i);

            if (token instanceof String) {
                String operator = (String) token;

                switch (operator) {
                    case "+": System.out.println("Addition"); break;
                    case "-": System.out.println("Soustraction"); break;
                    case "*": System.out.println("Multiplication"); break;
                    case "/": System.out.println("Division"); break;
                }
            }
        }

    }
}
