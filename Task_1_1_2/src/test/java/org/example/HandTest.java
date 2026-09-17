package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Тесты для руки.
 */
class HandTest {

    private Hand hand;
    private Card twoSpades;
    private Card jackHearts;
    private Card aceClubs;
    private Card closedSevenDiamonds;

    @BeforeEach
    void set() {
        hand = new Hand();
        twoSpades = new Card(Rank.TWO, Suit.SPADES);
        jackHearts = new Card(Rank.JACK, Suit.HEARTS);
        aceClubs = new Card(Rank.ACE, Suit.CLUBS);
        closedSevenDiamonds = new Card(Rank.SEVEN, Suit.DIAMONDS);
        closedSevenDiamonds.setOpen(false);
    }

    @Test
    void testGetScore() {
        assertEquals(0, hand.getScore());
        hand.addCard(twoSpades);
        hand.addCard(closedSevenDiamonds);
        assertEquals(2, hand.getScore());
        hand.addCard(aceClubs);
        assertEquals(13, hand.getScore());
        hand.addCard(jackHearts);
        assertEquals(13, hand.getScore());
    }

    @Test
    void testOpenAllCards() {
        hand.addCard(closedSevenDiamonds);
        hand.openAllCards();
        assertTrue(closedSevenDiamonds.isOpen());
    }

    @Test
    void testToString() {
        hand.addCard(twoSpades);
        hand.addCard(aceClubs);
        assertEquals("[Двойка Пики (2), Туз Трефы (11)]", hand.toString());
        hand.addCard(jackHearts);
        assertEquals("[Двойка Пики (2), Туз Трефы (1), Валет Червы (10)]", hand.toString());
    }
}
