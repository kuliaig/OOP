package org.example;

/**
 * Число.
 */
public class Number extends Expression {

    private final int value;

    /**
     * Конструктор для числа.
     *
     * @param value значение, которое будет храниться
     */
    public Number(int value) {
        this.value = value;
    }

    @Override
    public Expression derivative(String variable) {
        return new Number(0);
    }

    @Override
    public int eval(ValueOfVar variables) {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public Expression simplify() {
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Number)) {
            return false;
        }

        Number number = (Number) obj;
        return value == number.value;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(value);
    }
}
