package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {
    private final Calculator calc = new Calculator();

    @Test
    void testAdd(){ assertEquals(5, calc.add(2, 3));}

    @Test
    void testMultiply(){ assertEquals(6, calc.multiply(2, 3));}
}
