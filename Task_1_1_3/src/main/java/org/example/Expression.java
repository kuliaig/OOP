package org.example;

/**
 * Выражение (общее для всех операций).
 */
public abstract class Expression {

    /**
     * Напечатать выражение.
     */
    public void print() {
        System.out.println(this);
    }

    /**
     * Выполняет символьное дифференцирование.
     *
     * @param variable заданная переменная
     * @return новое дифференцированное выражение
     */
    public abstract Expression derivative(String variable);

    /**
     * Вычислить значение выражения.
     *
     * @param variables означивание переменных в формате x = 10; y = 13
     * @return значение выражения
     */
    public abstract int eval(ValueOfVar variables);
}
