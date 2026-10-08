package org.example;

import java.util.Objects;

/**
 * Произведение.
 */
public class Mul extends Expression {

    private final Expression first;
    private final Expression second;

    /**
     * Создает произведение.
     *
     * @param first первый множитель
     * @param second второй множитель
     */
    public Mul(Expression first, Expression second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public Expression derivative(String variable) {
        return new Add(new Mul(first.derivative(variable), second),
                new Mul(first, second.derivative(variable)));
    }

    @Override
    public int eval(ValueOfVar variables) {
        return first.eval(variables) * second.eval(variables);
    }

    @Override
    public String toString() {
        return "(" + first + "*" + second + ")";
    }

    @Override
    public Expression simplify() {
        Expression simpleFirst = first.simplify();
        Expression simpleSecond = second.simplify();

        final ValueOfVar empty = new ValueOfVar("");

        if (isEq(simpleFirst, 0) || isEq(simpleSecond, 0)) {
            return new Number(0);
        }

        if (isEq(simpleFirst, 1)) {
            return simpleSecond;
        }

        if (isEq(simpleSecond, 1)) {
            return simpleFirst;
        }

        if (simpleFirst instanceof Number && simpleSecond instanceof Number) {
            return new Number(simpleFirst.eval(empty) * simpleSecond.eval(empty));
        }

        return new Mul(simpleFirst, simpleSecond);
    }

    private boolean isEq(Expression expr, int num) {
        final ValueOfVar empty = new ValueOfVar("");

        if (expr instanceof Number && expr.eval(empty) == num) {
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Mul)) {
            return false;
        }
        Mul mul = (Mul) obj;
        return first.equals(mul.first) && second.equals(mul.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
}
