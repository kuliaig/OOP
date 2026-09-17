package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Тесты для Rank.
 */
class RankTest {

    @ParameterizedTest
    @CsvSource({
        "TWO, 2",
        "THREE, 3",
        "FOUR, 4",
        "FIVE, 5",
        "SIX, 6",
        "SEVEN, 7",
        "EIGHT, 8",
        "NINE, 9",
        "TEN, 10",
        "JACK, 10",
        "QUEEN, 10",
        "KING, 10",
        "ACE, 11"
    })
    void testGetValue(Rank rank, int expected) {
        assertEquals(expected, rank.getValue());
    }

    @ParameterizedTest
    @CsvSource({
            "TWO, Двойка",
            "THREE, Тройка",
            "FOUR, Четверка",
            "FIVE, Пятерка",
            "SIX, Шестерка",
            "SEVEN, Семерка",
            "EIGHT, Восьмерка",
            "NINE, Девятка",
            "TEN, Десятка",
            "JACK, Валет",
            "QUEEN, Дама",
            "KING, Король",
            "ACE, Туз"
    })
    void testGetRussianName(Rank rank, String expected) {
        assertEquals(expected, rank.toString());
    }
}
