package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
        assertEquals(9, hand.getScore());
        hand.addCard(aceClubs);
        assertEquals(20, hand.getScore());
        hand.addCard(jackHearts);
        assertEquals(20, hand.getScore());
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

    @Test
    void testIsBlackJack() {
        hand.addCard(aceClubs);
        assertFalse(hand.isBlackjack());
        hand.addCard(jackHearts);
        assertTrue(hand.isBlackjack());
    }

    @Test
    void testIsTwentyOne() {
        hand.addCard(aceClubs);
        assertFalse(hand.isTwentyOne());
        hand.addCard(jackHearts);
        assertTrue(hand.isTwentyOne());
        hand.addCard(twoSpades);
        assertFalse(hand.isTwentyOne());
    }

    @Test
    void testIsBust() {
        hand.addCard(jackHearts);
        assertFalse(hand.isBust());
        hand.addCard(aceClubs);
        assertFalse(hand.isBust());
        hand.addCard(jackHearts);
        assertFalse(hand.isBust());
        hand.addCard(jackHearts);
        assertTrue(hand.isBust());
    }
}
