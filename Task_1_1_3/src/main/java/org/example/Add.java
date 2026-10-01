package org.example;

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
}
