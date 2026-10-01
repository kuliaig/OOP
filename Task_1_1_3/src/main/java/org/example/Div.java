package org.example;

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
            return new Number(simpleFirst.eval(null) / simpleSecond.eval(null));
        }

        return new Div(simpleFirst, simpleSecond);
    }
}
