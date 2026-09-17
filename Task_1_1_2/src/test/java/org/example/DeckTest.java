package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Тесты для Deck.
 */
class DeckTest {

    @Test
    void testGetSize() {
        Deck deck1 = new Deck(1);
        assertEquals(52, deck1.getSize());
        Deck deck3 = new Deck(3);
        assertEquals(156, deck3.getSize());
    }

    @Test
    void testGetCard() {
        Deck deck = new Deck(1);
        Card card1 = deck.getCard();
        Card card2 = deck.getCard();
        assertEquals(50, deck.getSize());
    }
}
