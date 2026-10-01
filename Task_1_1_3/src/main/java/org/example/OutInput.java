package org.example;

import java.util.Scanner;

/**
 * Система ввода-вывода.
 */
public class OutInput {

    private static Scanner scanner = new Scanner(System.in);

    /**
     * Обновляет сканер (для тестов).
     */
    static void resetScanner() {
        scanner = new Scanner(System.in);
    }

    /**
     * Запрашивает у пользователя выражение и проводит с ним операции.
     */
    public static void printProg() {
        Parser parser = new Parser();

        System.out.println("При записи арифметических выражений убедитесь, "
                + "что каждый оператор отделен скобками: (3+(5*6))");

        while (true) {
            try {
                System.out.print("Введите арифметическое выражение: ");
                String input = scanner.nextLine();

                if (input.isEmpty()) {
                    break;
                }

                Expression e = parser.parse(input);

                System.out.print("Введите значения переменных в формате x=13; y=15");
                String varsInput = scanner.nextLine();
                System.out.println(e + " = " + e.eval(new ValueOfVar(varsInput)));

                System.out.print("Введите переменную, по которой вы хотите "
                        + "дифференцировать выражение: ");
                String var = scanner.nextLine();
                System.out.println("Производная: " + e.derivative(var));

            } catch (IllegalArgumentException | ArithmeticException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
    }
}
