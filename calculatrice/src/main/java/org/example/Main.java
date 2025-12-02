package org.example;

import org.example.Calculator.Calculator;
import org.example.calculeStrategy.Addition;
import org.example.calculeStrategy.Division;
import org.example.calculeStrategy.Multiplication;
import org.example.calculeStrategy.Soustration;
import org.example.enumeration.Operator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        List<Object> number = new ArrayList<>();

        System.out.println("mettez votre calcule finissez par '=' et tapez entre  :");
        String line = scanner.nextLine().replaceAll("\\s+", "");
        String[] operatorEspace = line.split("(?<=[-+*/=()])|(?=[-+*/=()])");

        if (!line.matches("^[0-9+\\-*/()=]+$")) {
            System.out.println("le programme attend des nombres et des operateurs");
            return;
        }

        Stack<Object> temp = new Stack<>();

        for (String input : operatorEspace) {
            if (input.equals("=")) break;

            if (input.matches("\\d+")) {
                temp.push(Integer.parseInt(input));
            } else if (input.matches("[+\\-*/()]")) {

                if (input.equals(")")) {
                    List<Object> inside = new ArrayList<>();
                    while (!temp.isEmpty() && !temp.peek().equals("(")) {
                        inside.add(0, temp.pop());
                    }
                    if (!temp.isEmpty() && temp.peek().equals("(")) {
                        temp.pop();
                    }

                    int res = (Integer) inside.get(0);
                    Calculator calculator = null;
                    for (int i = 1; i < inside.size(); i += 2) {
                        String operatorStr = (String) inside.get(i);
                        int nextNumber = (Integer) inside.get(i + 1);

                        Operator op = Operator.fromSymbol(operatorStr);

                        switch (op) {
                            case ADDITION -> calculator = Calculator.getInstance(new Addition());
                            case SOUSTRACTION -> calculator = Calculator.getInstance(new Soustration());
                            case MULTIPLICATION -> calculator = Calculator.getInstance(new Multiplication());
                            case DIVISION -> calculator = Calculator.getInstance(new Division());
                        }

                        res = calculator.calculatorOperation(res, nextNumber);
                    }

                    temp.push(res);
                } else {
                    temp.push(input);
                }

            } else {
                System.out.println("Entrée invalide : " + input);
                return;
            }
        }

        number.addAll(temp);

        if (number.isEmpty() || !(number.get(0) instanceof Integer)) {
            System.out.println("le calcule n'est pas bon.");
            return;
        }

        int result = (Integer) number.get(0);
        Calculator calculator = null;

        for (int i = 1; i < number.size(); i += 2) {

            if (!(number.get(i) instanceof String) || !(number.get(i + 1) instanceof Integer)) {
                System.out.println("Calcul impossible : expression invalide.");
                return;
            }

            String operatorStr = (String) number.get(i);
            int nextNumber = (Integer) number.get(i + 1);

            Operator op = Operator.fromSymbol(operatorStr);

            switch (op) {
                case ADDITION -> calculator = Calculator.getInstance(new Addition());
                case SOUSTRACTION -> calculator = Calculator.getInstance(new Soustration());
                case MULTIPLICATION -> calculator = Calculator.getInstance(new Multiplication());
                case DIVISION -> calculator = Calculator.getInstance(new Division());
            }

            result = calculator.calculatorOperation(result, nextNumber);
        }

        System.out.println("Résultat : " + result);
    }
}
