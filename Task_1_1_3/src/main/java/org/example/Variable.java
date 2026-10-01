package org.example;

/**
 * Переменная.
 */
public class Variable extends Expression {

    private final String name;

    /**
     * Конструктор для переменной.
     *
     * @param name название переменной
     */
    public Variable(String name) {
        this.name = name;
    }

    @Override
    public Expression derivative(String variable) {
        if (variable.equals(name)) {
            return new Number(1);
        }
        return new Number(0);
    }

    @Override
    public int eval(ValueOfVar variables) {
        return variables.get(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
