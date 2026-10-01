package org.example;

/**
 * Парсит строку в выражение.
 */
public class Parser {

    /**
     * Парсит строку (для выражений со строгими скобками).
     *
     * @param input строка
     * @return распарсенное выражение
     */
    public Expression parseWithBrackets(String input) {
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
        Expression first = parseWithBrackets(withoutBrackets.substring(0, signIndex));
        Expression second = parseWithBrackets(withoutBrackets.substring(signIndex + 1));

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
     * Парсит строку (для выражений без скобок).
     *
     * @param input строка
     * @return распарсенное выражение
     */
    public Expression parse(String input) {
        input = input.trim().replace(" ", "");

        if (input.isEmpty()) {
            throw new IllegalArgumentException("Пустая строка");
        }

        if (input.charAt(0) == '(' && findMatchBracket(input, 0) == input.length() - 1) {
            return parse(input.substring(1, input.length() - 1));
        }

        if (isNumber(input)) {
            return new Number(Integer.parseInt(input));
        }

        if (isVariable(input)) {
            return new Variable(input);
        }

        int indexOfSign = indexOfAddSub(input);
        if (indexOfSign != -1) {
            String first = input.substring(0, indexOfSign);
            String second = input.substring(indexOfSign + 1);

            char operation = input.charAt(indexOfSign);

            switch (operation) {
                case '+':
                    return new Add(parse(first), parse(second));
                case '-':
                    return new Sub(parse(first), parse(second));
                default:
                    throw new IllegalArgumentException("Неизвестная операция");
            }
        }

        indexOfSign = indexOfMulDiv(input);
        if (indexOfSign != -1) {
            String left = input.substring(0, indexOfSign);
            String right = input.substring(indexOfSign + 1);
            char operation = input.charAt(indexOfSign);

            switch (operation) {
                case '*':
                    return new Mul(parse(left), parse(right));
                case '/':
                    return new Div(parse(left), parse(right));
                default:
                    throw new IllegalArgumentException("Неизвестная операция");
            }
        }

        throw new IllegalArgumentException("Ошибка в записи выражения: " + input);
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

    /**
     * Находит индекс знака +-.
     *
     * @param str строка
     * @return индекс знака
     */
    private int indexOfAddSub(String str) {
        int depth = 0;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if (c == '(') {
                depth++;
            } else if (c == ')') {
                depth--;
            } else if (depth == 0 && (c == '+' || c == '-')) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Находит индекс знака +-.
     *
     * @param str строка
     * @return индекс знака
     */
    private int indexOfMulDiv(String str) {
        int depth = 0;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if (c == '(') {
                depth++;
            } else if (c == ')') {
                depth--;
            } else if (depth == 0 && (c == '*' || c == '/')) {
                return i;
            }
        }

        return -1;
    }
}