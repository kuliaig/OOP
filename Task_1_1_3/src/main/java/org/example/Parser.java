package org.example;

/**
 * Парсит строку в выражение.
 */
public class Parser {

    /**
     * Парсит строку.
     *
     * @param input строка
     * @return распарсенное выражение
     */
    public Expression parse(String input) {
        input = input.trim().replace(" ", "");

        if (input.isEmpty()) {
            throw new IllegalArgumentException("Пустая строка");
        }

        if (isNumber(input)) {
            return new Number(Integer.parseInt(input));
        }

        if (isVariable(input)) {
            return new Variable(input);
        }

        if (input.charAt(0) != '(' || findMatchBracket(input, 0) != input.length() - 1) {
            throw new IllegalArgumentException("Арифметическая операция не обёрнута в скобки");
        }

        String withoutBrackets = input.substring(1, input.length() - 1);
        int signIndex = indexOfSign(withoutBrackets);
        Expression first = parse(withoutBrackets.substring(0, signIndex));
        Expression second = parse(withoutBrackets.substring(signIndex + 1));

        switch (withoutBrackets.charAt(signIndex)) {
            case '+':
                return new Add(first, second);
            case '-':
                return new Sub(first, second);
            case '*':
                return new Mul(first, second);
            case '/':
                return new Div(first, second);
            default:
                throw new IllegalArgumentException("Ошибка в записи выражения: " + input);
        }
    }

    /**
     * Находит индекс парной закрывающей скобки.
     *
     * @param str строка
     * @param index индекс скобки "("
     * @return индекс парной ")"
     * @throws IllegalArgumentException если парная не найдена
     */
    private int findMatchBracket(String str, int index) {
        if (str.charAt(index) != '(') {
            throw new IllegalArgumentException("Не скобка на позиции " + index);
        }

        int depth = 0;

        for (int i = index; i < str.length(); i++) {
            char c = str.charAt(i);

            if (c == '(') {
                depth++;
            } else if (c == ')') {
                depth--;
                if (depth == 0) {
                    return i;
                }
            }
        }

        throw new IllegalArgumentException("Нет парной скобки для позиции " + index);
    }

    private boolean isNumber(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isVariable(String str) {
        return str.matches("[a-zA-Z][a-zA-Z0-9]*");
    }

    /**
     * Находит индекс знака арифметической операции.
     *
     * @param str строка
     * @return индекс знака
     */
    private int indexOfSign(String str) {
        int depth = 0;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if (c == '(') {
                depth++;
            } else if (c == ')') {
                depth--;
            } else if (depth == 0 && (c == '+' || c == '-' || c == '*' || c == '/')) {
                return i;
            }
        }

        throw new IllegalArgumentException("Не найдена операция в: " + str);
    }
}