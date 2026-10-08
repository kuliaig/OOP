package org.example;

import java.util.Objects;

/**
 * Деление.
 */
public class Div extends Expression {

    private final Expression first;
    private final Expression second;

    /**
     * Создает деление.
     *
     * @param first делимое
     * @param second делитель
     */
    public Div(Expression first, Expression second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public Expression derivative(String variable) {
        return new Div(new Sub(new Mul(first.derivative(variable), second),
                new Mul(first, second.derivative(variable))),
                new Mul(second, second));
    }

    @Override
    public int eval(ValueOfVar variables) {
        int secValue = second.eval(variables);
        if (secValue == 0) {
            throw new ArithmeticException("Деление на ноль");
        }
        return first.eval(variables) / secValue;
    }

    @Override
    public String toString() {
        return "(" + first + "/" + second + ")";
    }

    @Override
    public Expression simplify() {
        Expression simpleFirst = first.simplify();
        Expression simpleSecond = second.simplify();

        if (simpleFirst instanceof Number && simpleSecond instanceof Number) {
            int den = simpleSecond.eval(null);
            if (den == 0) {
                throw new ArithmeticException("Деление на ноль");
            }
            return new Number(simpleFirst.eval(null) / den);
        }

        return new Div(simpleFirst, simpleSecond);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Div)) {
            return false;
        }

        Div div = (Div) obj;
        return first.equals(div.first) && second.equals(div.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
}
