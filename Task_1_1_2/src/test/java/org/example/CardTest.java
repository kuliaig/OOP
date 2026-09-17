package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Тесты для Card
 */
class CardTest {

    private Card twoSpades;
    private Card jackHearts;
    private Card aceClubs;
    private Card closedSevenDiamonds;

    @BeforeEach
    void set() {
        twoSpades = new Card(Rank.TWO, Suit.SPADES);
        jackHearts = new Card(Rank.JACK, Suit.HEARTS);
        aceClubs = new Card(Rank.ACE, Suit.CLUBS);
        closedSevenDiamonds = new Card(Rank.SEVEN, Suit.DIAMONDS);
        closedSevenDiamonds.setOpen(false);
    }

    @Test
    void testGetValue() {
        assertEquals(2, twoSpades.getValue());
        assertEquals(10, jackHearts.getValue());
        assertEquals(11, aceClubs.getValue());
        assertEquals(7, closedSevenDiamonds.getValue());
    }

    @Test
    void testGetRank() {
        assertEquals(Rank.TWO, twoSpades.getRank());
        assertEquals(Rank.JACK, jackHearts.getRank());
        assertEquals(Rank.ACE, aceClubs.getRank());
        assertEquals(Rank.SEVEN, closedSevenDiamonds.getRank());
    }

    @Test
    void testGetSuit() {
        assertEquals(Suit.SPADES, twoSpades.getSuit());
        assertEquals(Suit.HEARTS, jackHearts.getSuit());
        assertEquals(Suit.CLUBS, aceClubs.getSuit());
        assertEquals(Suit.DIAMONDS, closedSevenDiamonds.getSuit());
    }

    @Test
    void testIsOpen() {
        assertTrue(twoSpades.isOpen());
        assertTrue(jackHearts.isOpen());
        assertTrue(aceClubs.isOpen());
        assertFalse(closedSevenDiamonds.isOpen());
    }

    @Test
    void testToRussian() {
        assertEquals("Двойка Пики (2)", twoSpades.toRussian(false));
        assertEquals("Валет Червы (10)", jackHearts.toRussian(false));
        assertEquals("Туз Трефы (1)", aceClubs.toRussian(true));
        assertEquals("<закрытая карта>", closedSevenDiamonds.toRussian(false));
    }
}
