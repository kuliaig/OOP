package org.example;

import java.util.Objects;

/**
 * Сумма.
 */
public class Add extends Expression {

    private final Expression first;
    private final Expression second;

    /**
     * Создает сумму.
     *
     * @param first первое слагаемое
     * @param second второе слагаемое
     */
    public Add(Expression first, Expression second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public Expression derivative(String variable) {
        return new Add(first.derivative(variable), second.derivative(variable));
    }

    @Override
    public int eval(ValueOfVar variables) {
        return first.eval(variables) + second.eval(variables);
    }

    @Override
    public String toString() {
        return "(" + first + "+" + second + ")";
    }

    @Override
    public Expression simplify() {
        Expression simpleFirst = first.simplify();
        Expression simpleSecond = second.simplify();

        if (simpleFirst instanceof Number && simpleSecond instanceof Number) {
            return new Number(simpleFirst.eval(null) + simpleSecond.eval(null));
        }

        return new Add(simpleFirst, simpleSecond);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Add)) {
            return false;
        }

        Add add = (Add) obj;
        return first.equals(add.first) && second.equals(add.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
}
