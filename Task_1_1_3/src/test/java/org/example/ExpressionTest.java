package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


/**
 * Тесты для всех типов expression.
 */
class ExpressionTest {

    private Expression expr;
    private Expression second;

    @BeforeEach
    void set() {
        expr = new Add(new Number(3), new Mul(new Number(2),
                new Variable("x")));
        second = new Sub(new Div(new Number(20), new Number(2)), new Number(6));
    }

    @Test
    void stringTest() {
        assertEquals(expr.toString(), "(3+(2*x))");
        assertEquals(second.toString(), "((20/2)-6)");
    }

    @Test
    void derivativeTest() {
        assertEquals(expr.derivative("x").toString(), "(0+((0*x)+(2*1)))");
        assertEquals(second.derivative("").toString(), "((((0*2)-(20*0))/(2*2))-0)");
    }

    @Test
    void evalTest() {
        assertEquals(expr.eval(new ValueOfVar("x = 10; y = 13")), 23);
        assertEquals(second.eval(new ValueOfVar(" ")), 4);
        Expression third = new Div(new Number(1), new Number(0));
        assertThrows(ArithmeticException.class, () -> third.eval(new ValueOfVar(" ")));
    }

    @Test
    void printTest() {
        assertDoesNotThrow(expr::print);
        assertDoesNotThrow(second::print);
    }
}
