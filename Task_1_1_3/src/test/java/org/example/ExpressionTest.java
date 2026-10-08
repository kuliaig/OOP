package org.example;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

    @Test
    void simplifyVar() {
        Expression var = new Variable("x");
        assertEquals(var, var.simplify());
    }

    @Test
    void simplifyNumberComplex() {
        assertEquals("22",
                new Mul(new Add(new Number(5), new Number(6)), new Number(2))
                        .simplify().toString());
    }

    @Test
    void simplifyMulZeroOne() {
        assertEquals("0",
                new Mul(new Number(0), new Variable("y")).simplify().toString());
    }

    @Test
    void simplifyMulZeroTwo() {
        assertEquals("0",
                new Mul(new Variable("x"), new Number(0)).simplify().toString());
    }

    @Test
    void simplifyMulByOne() {
        assertEquals("x",
                new Mul(new Variable("x"), new Number(1)).simplify().toString());
    }

    @Test
    void simplifySubSame() {
        assertEquals("0",
                new Sub(new Variable("x"), new Variable("x")).simplify().toString());
    }

    @Test
    void simplifyOriginal() {
        Expression original = new Mul(new Number(0), new Variable("x"));
        Expression simplified = original.simplify();

        assertEquals("(0*x)", original.toString());
        assertEquals("0", simplified.toString());
    }

    @Test
    void simplifySubNumbers() {
        assertEquals("7",
                new Sub(new Number(10), new Number(3)).simplify().toString());
    }

    @Test
    void simplifyDivNumbers() {
        assertEquals("5",
                new Div(new Number(20), new Number(4)).simplify().toString());
    }

    @Test
    void simplifyDivNested() {
        assertEquals("1",
                new Div(new Add(new Number(2), new Number(3)), new Number(5))
                        .simplify().toString());
    }

    @Test
    void simplifyDivNull() {
        Expression third = new Div(new Number(1), new Number(0));
        assertThrows(ArithmeticException.class, () -> third.simplify());
    }

    @Test
    void equalNumVarTest() {
        assertTrue(new Number(5).equals(new Number(5)));
        assertFalse(new Number(5).equals(new Number(7)));
        assertFalse(new Number(5).equals(new Variable("x")));
        assertTrue(new Variable("x").equals(new Variable("x")));
        assertFalse(new Variable("x").equals(new Variable("y")));
    }

    @Test
    void equalOperTest() {
        Expression first = new Number(5);
        Expression second = new Number(7);
        Expression third = new Variable("x");

        assertTrue(new Add(first, second).equals(new Add(first, second)));
        assertFalse(new Add(first, second).equals(new Add(third, second)));
        assertTrue(new Sub(first, second).equals(new Sub(first, second)));
        assertFalse(new Sub(first, second).equals(new Sub(third, second)));
        assertTrue(new Mul(first, second).equals(new Mul(first, second)));
        assertFalse(new Mul(first, second).equals(new Mul(third, second)));
        assertTrue(new Div(first, second).equals(new Div(first, second)));
        assertFalse(new Div(first, second).equals(new Div(third, second)));
        assertFalse(new Add(first, second).equals(new Sub(first, second)));
        assertFalse(new Mul(first, second).equals(new Div(first, second)));
    }

    @Test
    void variableHashCode() {
        assertEquals(new Variable("x").hashCode(), new Variable("x").hashCode());
    }

    @Test
    void variableDerivative() {
        assertEquals("1", new Variable("x").derivative("x").toString());
        assertEquals("0", new Variable("x").derivative("y").toString());
    }
}