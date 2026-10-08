package org.example;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Тесты для ввода-вывода.
 */
class OutInputTest {

    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final java.io.InputStream originalIn = System.in;

    @BeforeEach
    void setBefore() {
        System.setOut(new PrintStream(out));
    }

    @AfterEach
    void setAfter() {
        System.setIn(System.in);
        System.setOut(System.out);
    }

    private void setInput(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        OutInput.resetScanner();
    }

    @Test
    void testExampleWithBrackets() {
        setInput("(3+(2*x))\nx = 10\nx\n\n");
        OutInput.printProg();

        String output = out.toString();
        assertTrue(output.contains("(3+(2*x))"));
        assertTrue(output.contains("23"));
    }

    @Test
    void testExample() {
        setInput("3+2*x\nx = 10\nx\n\n");
        OutInput.printProg();

        String output = out.toString();
        assertTrue(output.contains("(3+(2*x))"));
        assertTrue(output.contains("23"));
    }

    @Test
    void testError() {
        setInput("3+2*x\n(3+2)\n\n\n\n");

        OutInput.printProg();

        String output = out.toString();
        assertTrue(output.contains("Ошибка"));
    }

    @Test
    void testExit() {
        setInput("\n");

        assertDoesNotThrow(OutInput::printProg);
    }

    @Test
    void testSub() {
        setInput("10-3-2\n \nx\n\n");
        OutInput.printProg();

        String output = out.toString();
        assertTrue(output.contains("((10-3)-2)"));
        assertTrue(output.contains("5"));
    }
}
