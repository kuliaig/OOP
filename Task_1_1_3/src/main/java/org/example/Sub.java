package org.example;

import java.util.Objects;

/**
 * Разность.
 */
public class Sub extends Expression {

    private final Expression first;
    private final Expression second;

    /**
     * Создает разность.
     *
     * @param first уменьшаемое
     * @param second вычитаемое
     */
    public Sub(Expression first, Expression second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public Expression derivative(String variable) {
        return new Sub(first.derivative(variable), second.derivative(variable));
    }

    @Override
    public int eval(ValueOfVar variables) {
        return first.eval(variables) - second.eval(variables);
    }

    @Override
    public String toString() {
        return "(" + first + "-" + second + ")";
    }

    @Override
    public Expression simplify() {
        Expression simpleFirst = first.simplify();
        Expression simpleSecond = second.simplify();

        if (simpleFirst.equals(simpleSecond)) {
            return new Number(0);
        }

        if (simpleFirst instanceof Number && simpleSecond instanceof Number) {
            return new Number(simpleFirst.eval(null) - simpleSecond.eval(null));
        }

        return new Sub(simpleFirst, simpleSecond);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Sub)) {
            return false;
        }

        Sub sub = (Sub) obj;
        return first.equals(sub.first) && second.equals(sub.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
}
