package org.example;

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
}
