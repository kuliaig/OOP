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

    @Override
    public Expression simplify() {
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Variable)) {
            return false;
        }

        Variable variable = (Variable) obj;
        return name.equals(variable.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
