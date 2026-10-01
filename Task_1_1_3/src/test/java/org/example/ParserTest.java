package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Тесты для парсера.
 */
class ParserTest {

    private final Parser parser = new Parser();

    @Test
    void testParseWithBracketsNumber() {
        Expression e = parser.parseWithBrackets("3");
        assertEquals("3", e.toString());
    }

    @Test
    void testParseWithBracketsVariable() {
        Expression e = parser.parseWithBrackets(" x12 ");
        assertEquals("x12", e.toString());
    }

    @Test
    void testParseWithBracketsAdd() {
        Expression e = parser.parseWithBrackets("(5+ x)");
        assertEquals("(5+x)", e.toString());
    }

    @Test
    void testParseWithBracketsMul() {
        Expression e = parser.parseWithBrackets("(3*2)");
        assertEquals("(3*2)", e.toString());
    }

    @Test
    void testParseWithBracketsSub() {
        Expression e = parser.parseWithBrackets("( 8 - 6 )");
        assertEquals("(8-6)", e.toString());
    }

    @Test
    void testParseWithBracketsDiv() {
        Expression e = parser.parseWithBrackets("(6/1)");
        assertEquals("(6/1)", e.toString());
    }

    @Test
    void testParseWithBracketsExpression() {
        Expression e = parser.parseWithBrackets("(5+(3*(8/2)))");
        assertEquals("(5+(3*(8/2)))", e.toString());
    }

    @Test
    void testParseWithBracketsErrors() {
        assertThrows(IllegalArgumentException.class, () -> parser.parseWithBrackets(""));
        assertThrows(IllegalArgumentException.class, () -> parser.parseWithBrackets("3+2"));
        assertThrows(IllegalArgumentException.class, () -> parser.parseWithBrackets("(3+2"));
        assertThrows(IllegalArgumentException.class, () -> parser.parseWithBrackets("2+3)"));
        assertThrows(IllegalArgumentException.class, () -> parser.parseWithBrackets("(5*2)+(3*1)"));
    }

    @Test
    void testParseNumber() {
        Expression e = parser.parse("3");
        assertEquals("3", e.toString());
    }

    @Test
    void testParseVariable() {
        Expression e = parser.parse(" x12 ");
        assertEquals("x12", e.toString());
    }

    @Test
    void testParseAdd() {
        Expression e = parser.parse("5+ x");
        assertEquals("(5+x)", e.toString());
    }

    @Test
    void testParseMul() {
        Expression e = parser.parse("3*2");
        assertEquals("(3*2)", e.toString());
    }

    @Test
    void testParseSub() {
        Expression e = parser.parse(" 8 - 6 ");
        assertEquals("(8-6)", e.toString());
    }

    @Test
    void testParseDiv() {
        Expression e = parser.parse("(6/1)");
        assertEquals("(6/1)", e.toString());
    }

    @Test
    void testParseExpression() {
        Expression e = parser.parse("5+3*(8/2)");
        assertEquals("(5+(3*(8/2)))", e.toString());
    }

    @Test
    void testParseErrors() {
        assertThrows(IllegalArgumentException.class, () -> parser.parse(""));
        assertThrows(IllegalArgumentException.class, () -> parser.parse("(3+2"));
        assertThrows(IllegalArgumentException.class, () -> parser.parse("2+3)"));
    }
}
