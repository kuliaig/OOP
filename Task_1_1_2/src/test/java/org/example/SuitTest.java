package org.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Тесты для Rank
 */
class SuitTest {

    @ParameterizedTest
    @CsvSource({
            "SPADES, Пики",
            "HEARTS, Червы",
            "DIAMONDS, Бубны",
            "CLUBS, Трефы"
    })
    void testGetRussianName(Suit suit, String expected) {
        assertEquals(expected, suit.toString());
    }
}
